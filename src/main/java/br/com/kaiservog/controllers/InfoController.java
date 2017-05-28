package br.com.kaiservog.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.kaiservog.cript.Criptography;
import br.com.kaiservog.cript.qrcode.QRGenerator;
import br.com.kaiservog.daos.InfoDao;
import br.com.kaiservog.models.CriptInfo;
import br.com.kaiservog.models.Info;

@Controller
@RequestMapping("/info")
@Transactional
public class InfoController {

	@Autowired
	private InfoDao infoDao;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void save(@Valid Info info, BindingResult bindingResult) {
		infoDao.updateOrSave(info);
	}

	@RequestMapping(value = "/save-secret", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void saveWithCript(CriptInfo criptInfo) {
		Criptography c = new Criptography();

		try {
			String criptValue = c.cript(criptInfo.getValue(), criptInfo.getKey());
			Info info = new Info();
			
			info.setService(criptInfo.getService());
			info.setValue(criptValue);
			infoDao.updateOrSave(info);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/search/{service}", method = RequestMethod.GET)
	public @ResponseBody Info search(@PathVariable("service") String service) {
		return infoDao.search(service);
	}
	
	@RequestMapping(value = "/search-qrcode/{service}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> searchQRCode(@PathVariable("service") String service) {
		Info info = infoDao.search(service);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new QRGenerator().generateJpg(info.getValue()).toByteArray());
	}
	
	
}
