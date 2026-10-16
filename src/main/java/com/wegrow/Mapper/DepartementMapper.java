package com.wegrow.Mapper;

import com.wegrow.dto.DepartementResponseDto;
import com.wegrow.entity.Departement;

public class DepartementMapper {

    public DepartementResponseDto dto(Departement departement,int nombresEmployes){

        if (departement == null) {
            return null;
        }

        DepartementResponseDto dep=new DepartementResponseDto(departement.getDescription(), departement.getId(),
                departement.getNom(), nombresEmployes);


        return dep;

    }
}
