package com.wegrow.Mapper;

import com.wegrow.dto.EmployeResponseDto;
import com.wegrow.entity.Employe;

public class EmployeMapper {
public EmployeResponseDto dto(Employe employe){

    EmployeResponseDto employ=new EmployeResponseDto(employe.getId(), employe.getMatricule(),
            employe.getNom(), employe.getPrenom());
    return employ;
}
}
