package HDmedi.Server.domain.child_medicine.service.Impl;


import HDmedi.Server.domain.alarm.entity.Alarm;
import HDmedi.Server.domain.alarm.repository.AlramRepository;
import HDmedi.Server.domain.alarm_date.entity.AlarmDate;
import HDmedi.Server.domain.alarm_date.repository.AlarmDateRepository;
import HDmedi.Server.domain.child_medicine.dto.request.EnrollMedicineRequestDto;
import HDmedi.Server.domain.child_medicine.dto.response.DoseRecordResponseDto;
import HDmedi.Server.domain.child_medicine.dto.response.MedicineManageResponseDto;
import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.child_medicine.repository.ChildMedicineRepository;
import HDmedi.Server.domain.child_medicine.service.ChildMedicineService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Transactional
@Service
public class ChildMedicineServiceImpl implements ChildMedicineService {

    public final UserChildRepository userChildRepository;
    public final ChildMedicineRepository childMedicineRepository;
    public final MedicineItemRepository medicineItemRepository;
    public final MedicinesRepository medicinesRepository;
    public final AlramRepository alramRepository;
    public final AlarmDateRepository alarmDateRepository;


    private final Logger LOGGER = LoggerFactory.getLogger(ChildMedicineServiceImpl.class);
    @Autowired
    ChildMedicineServiceImpl(UserChildRepository userChildRepository, ChildMedicineRepository childMedicineRepository,
                             MedicineItemRepository medicineItemRepository,
                             MedicinesRepository medicinesRepository,
                             AlramRepository alramRepository,
                             AlarmDateRepository alarmDateRepository){
        this.userChildRepository = userChildRepository;
        this.childMedicineRepository = childMedicineRepository;
        this.medicineItemRepository = medicineItemRepository;
        this.medicinesRepository = medicinesRepository;
        this.alramRepository = alramRepository;
        this.alarmDateRepository = alarmDateRepository;
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
        medicineManageResponseDto.setMessage("OK");
        medicineManageResponseDto.setCode(200);

        return medicineManageResponseDto;
    }

    @Override
    public DoseRecordResponseDto doseRecord(Long userId) {

        UserEntity userEntity = UserEntity.builder().id(userId).build();

        List<UserChild> userChildList = userChildRepository.findByUserEntity(userEntity);  // 반복문
        //      List<ChildMedicine> childMedicineList = childMedicineRepository.findByUserChild(userChild);  //반복문
        //     List<Alarm> alarmList = alramRepository.findAllByChildMedicine(childMedicineList); // 반복문

       AlarmDate alarmDate = alarmDateRepository.findByAlramDate(LocalDate.now());

       List<DoseRecordResponseDto.DoseCharacterDto> doseCharacterDtos = new ArrayList<>(userChildList.size());

       for( UserChild userChild : userChildList){

           List<DoseRecordResponseDto.DoseAlarmDto> doseAlarmDtos = new ArrayList<>();

           int i = 0;
           for(ChildMedicine childMedicine : userChild.getChildMedicines()){

               DoseRecordResponseDto.DoseAlarmDto doseAlarmDtoList = DoseRecordResponseDto.DoseAlarmDto.builder()  // 여기서 index문제
                       .doseSign(childMedicine.getAlarms().get(i).getAlramDates().get(i).getDoseSign())
                       .label(childMedicine.getAlarms().get(i).getLabel())
                       .time(childMedicine.getAlarms().get(i).getTime())
                       .count(childMedicine.getMedicines().size())
                       .build();
               i++;
               doseAlarmDtos.add(doseAlarmDtoList);
           }

           DoseRecordResponseDto.DoseCharacterDto doseCharacterDto = DoseRecordResponseDto.DoseCharacterDto.builder()
                   .character(userChild.getName())
                   .doseAlarmList(doseAlarmDtos)
                   .build();

           doseCharacterDtos.add(doseCharacterDto);
       }

       DoseRecordResponseDto doseRecordResponseDto = DoseRecordResponseDto.builder()
               .characterList(doseCharacterDtos)
               .code(200)
               .message("OK")
               .build();

        return doseRecordResponseDto;
    }
}
