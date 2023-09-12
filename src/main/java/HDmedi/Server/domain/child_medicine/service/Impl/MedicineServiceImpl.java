package HDmedi.Server.domain.child_medicine.service.Impl;


import HDmedi.Server.domain.child_medicine.controller.MedicineController;
import HDmedi.Server.domain.child_medicine.dto.request.EnrollMedicineRequestDto;
import HDmedi.Server.domain.child_medicine.dto.response.MedicineManageResponseDto;
import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.child_medicine.repository.ChildMedicineRepository;
import HDmedi.Server.domain.child_medicine.service.MedicineService;
import HDmedi.Server.domain.medicine_item.entity.MedicineItem;
import HDmedi.Server.domain.medicine_item.repository.MedicineItemRepository;
import HDmedi.Server.domain.medicines.entity.Medicines;
import HDmedi.Server.domain.medicines.repository.MedicinesRepository;
import HDmedi.Server.domain.tag.entity.Tag;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_child.repository.UserChildRepository;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import HDmedi.Server.global.exception.CustomExceptionContext;
import HDmedi.Server.global.exception.HDmediException;
import HDmedi.Server.global.exception.notfound.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MedicineServiceImpl implements MedicineService {

    UserChildRepository userChildRepository;
    ChildMedicineRepository childMedicineRepository;
    MedicineItemRepository medicineItemRepository;
    MedicinesRepository medicinesRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(MedicineServiceImpl.class);
    @Autowired
    MedicineServiceImpl(UserChildRepository userChildRepository,ChildMedicineRepository childMedicineRepository,
                        MedicineItemRepository medicineItemRepository,
                        MedicinesRepository medicinesRepository
    ){
        this.userChildRepository = userChildRepository;
        this.childMedicineRepository = childMedicineRepository;
        this.medicineItemRepository = medicineItemRepository;
        this.medicinesRepository = medicinesRepository;
    }
    @Override
    public ResponseDto enrollMedicine(Long userId, EnrollMedicineRequestDto enrollMedicineRequestDto) {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);


        UserChild userChild = userChildRepository.findByNameAndUserEntity(enrollMedicineRequestDto.getCharacterName(), userEntity);

LOGGER.info(String.valueOf(userChild.getId()));

        if(userChild == null){
            throw new HDmediException(CustomExceptionContext.INVALID_MEMBER_ID_ERROR.getHttpStatus(),
                    CustomExceptionContext.INVALID_MEMBER_ID_ERROR.getMessage(),
                    CustomExceptionContext.INVALID_MEMBER_ID_ERROR.getCode()
                    );
        }

        ChildMedicine childMedicine = new ChildMedicine();

        childMedicine.setPurpose(enrollMedicineRequestDto.getPurpose());
        childMedicine.setStartDate(enrollMedicineRequestDto.getStartDate());
        childMedicine.setEndDate(enrollMedicineRequestDto.getEndDate());
        childMedicine.setUserChild(userChild);

        ChildMedicine savedChildMedicine = childMedicineRepository.save(childMedicine);


        String[] medicineName = enrollMedicineRequestDto.getMedicine();


        for(int i = 0; i < enrollMedicineRequestDto.getMedicine().length;i++){
            MedicineItem medicineItem = medicineItemRepository.findByName(medicineName[i]);
            Medicines medicines = new Medicines();
            medicines.setChildMedicine(savedChildMedicine);
            medicines.setMedicineItem(medicineItem);
            medicinesRepository.save(medicines);
        }

        ResponseDto responseDto = new ResponseDto();

        responseDto.setMessage("완료");
        responseDto.setCode(200);


        return responseDto;
    }

    @Override
    @Transactional
    public MedicineManageResponseDto selectMedicineManage(Long userId) {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        List<UserChild> userChildList = userChildRepository.findByUserEntity(userEntity);



        List<MedicineManageResponseDto.CharacterDTO> characterDTOS = new ArrayList<>(userChildList.size());

        for (UserChild userChild : userChildList) {
            MedicineManageResponseDto.CharacterDTO characterDTO = new MedicineManageResponseDto.CharacterDTO();
            characterDTO.setCharacterName(userChild.getName());

            List<MedicineManageResponseDto.EnrollMedicineDTO> enrollMedicineDTOS = new ArrayList<>();

            for (ChildMedicine childMedicine : userChild.getChildMedicines()) {
                MedicineManageResponseDto.EnrollMedicineDTO enrollMedicineDTO = new MedicineManageResponseDto.EnrollMedicineDTO();
                enrollMedicineDTO.setCharacterName(childMedicine.getPurpose());
                enrollMedicineDTO.setStartDate(String.valueOf(childMedicine.getStartDate()));
                enrollMedicineDTO.setEndDate(String.valueOf(childMedicine.getEndDate()));
                enrollMedicineDTO.setMedicineCount(childMedicine.getMedicines().size());

                List<MedicineManageResponseDto.MedicineDTO> medicineDTOS = new ArrayList<>();

                for (Medicines medicines : childMedicine.getMedicines()) {
                    MedicineManageResponseDto.MedicineDTO medicineDTO = new MedicineManageResponseDto.MedicineDTO();
                    medicineDTO.setName(medicines.getMedicineItem().getName());

                    List<MedicineManageResponseDto.TagDTO> tagDTOS = new ArrayList<>(medicines.getMedicineItem().getTags().size());

                    for(Tag tag : medicines.getMedicineItem().getTags()){
                        MedicineManageResponseDto.TagDTO tagDTO = new MedicineManageResponseDto.TagDTO();
                        tagDTO.setTag(tag.getType());


                        tagDTOS.add(tagDTO);
                    }
                    medicineDTO.setTagList(tagDTOS);

                    medicineDTOS.add(medicineDTO);
                }
                enrollMedicineDTO.setMedicineList(medicineDTOS);
                enrollMedicineDTOS.add(enrollMedicineDTO);
            }

            characterDTO.setEnrollMedicineList(enrollMedicineDTOS);
            characterDTOS.add(characterDTO);
        }

        MedicineManageResponseDto medicineManageResponseDto = new MedicineManageResponseDto();
        medicineManageResponseDto.setCharacterList(characterDTOS);

        return medicineManageResponseDto;
    }
}
