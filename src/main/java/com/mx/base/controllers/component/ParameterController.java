package com.mx.base.controllers.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mx.base.models.catalog.ParameterValues;
import com.mx.base.models.entity.ParameterValueEntity;
import com.mx.base.services.ParameterService;
import com.mx.base.util.response.JSONResponse;

@Controller
@RequestMapping("/parameter")
public class ParameterController {

	@Autowired
	private ParameterService parameterService;

	@RequestMapping(value = "map/{value}", method = RequestMethod.GET)
	public String findParameters(ModelMap model, @PathVariable("value") String value) {

		ParameterValues parameterValues;
		parameterValues = new ParameterValues();
		List<ParameterValueEntity> lst = (List<ParameterValueEntity>) parameterService.getParametersByCode(value);
		Map<String, String> values = new HashMap<String, String>();
		for (ParameterValueEntity param : lst) {
			values.put("" + param.getValue(), "" + param.getDescription());
		}
		parameterValues.setValues(values);
		model.addAttribute("parameterValues", parameterValues);

		return "paramValues";

	}

	@ResponseBody
	@RequestMapping(value = "/map/{value}", method = RequestMethod.POST)
	public JSONResponse updateParameters(ModelMap model, ParameterValues parameterValues) {

		parameterService.updateParametersByCode(parameterValues);
		JSONResponse response = new JSONResponse();

		return response;

	}

}
