package HDmedi.Server.domain.alarm.service.Impl;

import HDmedi.Server.domain.alarm.dto.request.EnrollRecordRequestDto;
import HDmedi.Server.domain.alarm.dto.request.MedicineAddRequestDto;
import HDmedi.Server.domain.alarm.dto.response.MedicineAddPageResponseDto;
import HDmedi.Server.domain.alarm.entity.Alarm;
import HDmedi.Server.domain.alarm.repository.AlramRepository;
import HDmedi.Server.domain.alarm.service.AlramService;
import HDmedi.Server.domain.alarm_date.entity.AlarmDate;
import HDmedi.Server.domain.alarm_date.repository.AlarmDateRepository;
import HDmedi.Server.domain.child_medicine.controller.ChildMedicineController;
import HDmedi.Server.domain.child_medicine.entity.ChildMedicine;
import HDmedi.Server.domain.child_medicine.repository.ChildMedicineRepository;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_child.repository.UserChildRepository;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Transactional
@Service
public class AlramServiceImpl implements AlramService {

    private final UserChildRepository userChildRepository;

    AlarmDateRepository alarmDateRepository;
    ChildMedicineRepository childMedicineRepository;
    AlramRepository alramRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(AlramServiceImpl.class);
    @Autowired
    public AlramServiceImpl(UserChildRepository userChildRepository,   AlarmDateRepository alarmDateRepository,
                            ChildMedicineRepository childMedicineRepository,AlramRepository alramRepository) {
        this.userChildRepository = userChildRepository;
        this.alarmDateRepository = alarmDateRepository;
        this.childMedicineRepository = childMedicineRepository;
        this.alramRepository = alramRepository;
    }


    @Override
    public ResponseDto enrollRecord(Long userId, EnrollRecordRequestDto enrollRecordRequestDto) {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        List<UserChild> userChild = userChildRepository.findByUserEntity(userEntity);

        
        return null;
    }

    @Override
    public MedicineAddPageResponseDto medicineAddPage(Long userId) {


        UserEntity userEntity = UserEntity.builder().id(userId).build();

        List<UserChild> userChildList = userChildRepository.findByUserEntity(userEntity);

        List<MedicineAddPageResponseDto.CharacterDto> characterDtos = new ArrayList<>(userChildList.size());

        for( UserChild userChild : userChildList){


            List<MedicineAddPageResponseDto.MedicineDto> medicineDtos = new ArrayList<>(userChildList.size());

            for(ChildMedicine childMedicine : userChild.getChildMedicines()){

                MedicineAddPageResponseDto.MedicineDto medicineDto = MedicineAddPageResponseDto.MedicineDto.builder()
                        .purpose(childMedicine.getPurpose()).build();

                medicineDtos.add(medicineDto);
            }

            MedicineAddPageResponseDto.CharacterDto characterDto1 = MedicineAddPageResponseDto.CharacterDto.builder()
                            .characterName(userChild.getName())
                                    .medicine(medicineDtos)
                                            .build();

            characterDtos.add(characterDto1);

        }

        MedicineAddPageResponseDto medicineAddPageResponseDto = MedicineAddPageResponseDto.builder()
                .characterData(characterDtos)
                .message("OK")
                .code(200)
                .build();


        return medicineAddPageResponseDto;
    }



    @Override
    public ResponseDto medicineAdd(Long userId, MedicineAddRequestDto medicineAddRequestDto) throws ParseException {

        UserEntity userEntity = UserEntity.builder().id(userId).build();

        UserChild userChild = userChildRepository.findByNameAndUserEntity(medicineAddRequestDto.getCharacter(),userEntity);

        ChildMedicine childMedicine = childMedicineRepository.findAllByPurposeAndUserChild(medicineAddRequestDto.getMedicine(), userChild);

        LOGGER.info(String.valueOf(childMedicine));

        Alarm alarm = Alarm.builder()
                .label(medicineAddRequestDto.getLabel())
                .startDate(medicineAddRequestDto.getStartDate())
                .endDate(medicineAddRequestDto.getEndDate())
                .childMedicine(childMedicine)
                .time(medicineAddRequestDto.getTime())
                .build();
        alramRepository.save(alarm);

        LOGGER.info("알람 추가 성공");

        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(medicineAddRequestDto.getStartDate()));

        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(medicineAddRequestDto.getEndDate()));

        List<String> dates = findDatesForWeekdaysInPeriod(
                startDate,
                endDate,
                medicineAddRequestDto.getDay()
                );


        for(int i = 0; i < dates.size(); i++){

            AlarmDate alarmDate = AlarmDate.builder()
                    .alramDate(LocalDate.parse(dates.get(i)))
                    .alarm(alarm)
                    .doseSign(false)
                    .build();

            alarmDateRepository.save(alarmDate);
        }

        LOGGER.info("알람 날짜 추가 성공");

        ResponseDto responseDto = ResponseDto.builder().code(200).message("OK").build();

        return responseDto;
    }


    public static List<String> findDatesForWeekdaysInPeriod(Date startDate, Date endDate, String[] weekdays) {
        List<String> result = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        while (!calendar.getTime().after(endDate)) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            for (String targetWeekday : weekdays) {
                if (dayOfWeek == getDayOfWeek(targetWeekday)) {
                    result.add(dateFormat.format(calendar.getTime()));
                }
            }
            calendar.add(Calendar.DATE, 1); // 다음 날짜로 이동
        }

        return result;
    }

    public static int getDayOfWeek(String weekday) {
        switch (weekday.toLowerCase()) {
            case "sunday":
                return Calendar.SUNDAY;
            case "monday":
                return Calendar.MONDAY;
            case "tuesday":
                return Calendar.TUESDAY;
            case "wednesday":
                return Calendar.WEDNESDAY;
            case "thursday":
                return Calendar.THURSDAY;
            case "friday":
                return Calendar.FRIDAY;
            case "saturday":
                return Calendar.SATURDAY;
            default:
                throw new IllegalArgumentException("Invalid weekday: " + weekday);
        }
    }










}
