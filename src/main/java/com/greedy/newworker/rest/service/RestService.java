package com.greedy.newworker.rest.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.newworker.rest.dto.RestDto;
import com.greedy.newworker.rest.entity.Rest;
import com.greedy.newworker.rest.entity.RestCate;
import com.greedy.newworker.rest.repository.RestRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestService {

	private final RestRepository restRepository;
	private final ModelMapper modelMapper;
	
	public RestService(RestRepository restRepository, ModelMapper modelMapper) {
		this.restRepository = restRepository;
		this.modelMapper = modelMapper;
	}
	/* 조회 */
	public Page<RestDto> selectRestList(int page) {
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("restNo").descending());
		 
		Page<Rest> RestList = restRepository.findAll(pageable);
		Page<RestDto> restDtoList = RestList.map(rest -> modelMapper.map(rest, RestDto.class));
		
		 
		return restDtoList;
	}
	
	
	
	

		
	/* 등록 */
	@Transactional
	public RestDto insertRest(RestDto restDto) {
		
		restRepository.save(modelMapper.map(restDto, Rest.class));
		
		return restDto;
		
	}
	
	
	/* 수정 */
	@Transactional
	public RestDto updateRest(RestDto restDto) {

		Rest foundRest = restRepository.findById(restDto.getRestNo())
				.orElseThrow(() -> new RuntimeException("존재하지 않는 휴가입니다."));
		
		foundRest.update(
				restDto.getRestDate(), 
				restDto.getRestModdate(),
				restDto.getRestDay(),
				restDto.getRestReason(),
				modelMapper.map(restDto.getRestCateTypeNo(), RestCate.class));
		
		restRepository.save(foundRest);
		
		return restDto;
	}
	
	
	/* 상세조회 */
	public RestDto selectRestDetail(Long restNo) { 
               
		RestDto restDto = modelMapper.map(restRepository.findById(restNo)
        		.orElseThrow(() -> new IllegalArgumentException("해당 휴가가 없습니다. restNo=" + restNo)), RestDto.class);
        
        
        
        return restDto;
	}
	
	/* 휴가 인가 페이지 */
	public Page<RestDto> selectRestListAdmin(int page) {
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("restNo").descending());
		
		Page<Rest> restListAdmin = restRepository.findByRestOk(pageable, "N");
		Page<RestDto> restDtoListAdmin = restListAdmin.map(rest -> modelMapper.map(rest, RestDto.class));
		
		
		
		return restDtoListAdmin;
	}
	
	/* 연차인가 상세조회 */
	public RestDto selectRestDetailAdmin(Long restNo) { 
               
		RestDto restDto = modelMapper.map(restRepository.findById(restNo)
        		.orElseThrow(() -> new IllegalArgumentException("해당 휴가가 없습니다. restNo=" + restNo)), RestDto.class);
        
        
        
        return restDto;
	}
	
	/* 연차 승인, 반려 */
	@Transactional
	public RestDto updateRestOk(RestDto restDto) {

		Rest foundRest = restRepository.findById(restDto.getRestNo())
				.orElseThrow(() -> new RuntimeException("존재하지 않는 휴가입니다."));
		
		foundRest.updateOk(
				restDto.getRestOk(), 
				restDto.getRestOkdate());
				
				
		
		restRepository.save(foundRest);
		
		return restDto;
	}

	

	
}
