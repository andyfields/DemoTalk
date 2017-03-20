package com.outlands.cooltalk.microservices.web;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.outlands.cooltalk.ctCommon.constants.OLSingleMessageConstants;
import com.outlands.cooltalk.ctEntities.entity.TMessageSection;
import com.outlands.cooltalk.microservices.dto.SectionDTO;
import com.outlands.cooltalk.microservices.service.SectionService;

@RestController
public class SectionsCtlr {
	
	@Autowired
	private SectionService sectionService;
	
	@Autowired
	private MessageSource messageSource;
	
    @RequestMapping(path="/micro/sections/findAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
	public List<SectionDTO> findAll() {
    	
    	List<SectionDTO> sectionDTOs = new ArrayList<>();
    	for (TMessageSection messageSection : sectionService.getAll()) {
    		sectionDTOs.add(new SectionDTO(messageSection.getId(), messageSection.getName()));
    	}
    	
    	return sectionDTOs;
    	
    }
    
    @RequestMapping(path="/micro/sections/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
	public SectionDTO create(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SectionDTO sectionDTO) {
    	
    	validateNameNotEmpty(request, sectionDTO.getName());
    	validateDuplicateSection(request, sectionDTO.getName());
    	
    	TMessageSection section = new TMessageSection(sectionDTO.getName(), new Date());
    	
    	sectionService.save(section);
    	
    	sectionDTO.setId(section.getId());
    	
    	return sectionDTO;
    }
    
    @RequestMapping(path="/micro/sections/edit", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
	public void edit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute SectionDTO sectionDTO) {
    	
    	TMessageSection section = getSectionById(request, sectionDTO.getId());
    	validateNameNotEmpty(request, sectionDTO.getName());
    	validateDuplicateSection(request, sectionDTO.getName());
    	
    	section.setName(sectionDTO.getName());
    	
    	sectionService.save(section);
    }
    
    private void validateNameNotEmpty(HttpServletRequest request, String name) {
    	
    	if (StringUtils.isEmpty(name)) 
    		throw new InvalidParameterException(messageSource.getMessage(OLSingleMessageConstants.SECTIONS_NAME_EMPTY.getMessageKey(), null, request.getLocale()));
    	
    }
    
    private void validateDuplicateSection(HttpServletRequest request, String name) {
    	
    	TMessageSection messageSection = sectionService.findByName(name);
    	if (messageSection != null) 
    		throw new InvalidParameterException(messageSource.getMessage(OLSingleMessageConstants.SECTIONS_CREATE_DUPLICATE_NAME.getMessageKey(), null, request.getLocale()));
    	
    }
    
    private TMessageSection getSectionById(HttpServletRequest request, long id) {
    	
    	TMessageSection messageSection = sectionService.findOne(id);
    	if (messageSection == null) 
    		throw new NoSuchElementException(messageSource.getMessage(OLSingleMessageConstants.SECTIONS_EDIT_NOTFOUND.getMessageKey(), null, request.getLocale()));
    	
    	return messageSection;
    }
    
    /**
     * Exception handler if invalid parameter is thrown in this Controller
     *
     * @param ex
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidParameterException.class)
    public String return400(InvalidParameterException ex) {
        return ex.getMessage();

    }
    
    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return404(NoSuchElementException ex) {
        return ex.getMessage();

    }
	


}
