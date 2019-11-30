package com.mx.base.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.base.repository.OutPutRepository;
import com.mx.base.services.OutPutInventoryService;

@Service
public class OutPutInventoryImpl implements OutPutInventoryService{

	@Autowired
	private OutPutRepository outPutRepository;
	
	@Override
	public void saveOutPut(Object out) {
		outPutRepository.saveOutPut(out);
	}

	@Override
	public Object getInventoryOutPut(int year, Long pk) {
		return outPutRepository.getInventoryOutPut(year, pk);
	}

}
