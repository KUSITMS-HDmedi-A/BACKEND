package HDmedi.Server.domain.alarm.service.Impl;

import HDmedi.Server.domain.alarm.dto.request.EnrollRecordRequestDto;
import HDmedi.Server.domain.alarm.dto.request.MedicineAddRequestDto;
import HDmedi.Server.domain.alarm.dto.response.MedicineAddPageResponseDto;
import HDmedi.Server.domain.alarm.service.AlramService;
import HDmedi.Server.domain.user_child.dto.response.ResponseDto;
import HDmedi.Server.domain.user_child.entity.UserChild;
import HDmedi.Server.domain.user_child.repository.UserChildRepository;
import HDmedi.Server.domain.user_entity.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class AlramServiceImpl implements AlramService {

    private final UserChildRepository userChildRepository;


    @Autowired
    public AlramServiceImpl(UserChildRepository userChildRepository) {
        this.userChildRepository = userChildRepository;
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





        return null;
    }

    @Override
    public ResponseDto medicineAdd(Long userId, MedicineAddRequestDto medicineAddRequestDto) throws ParseException {


        Date startDate = new SimpleDateFormat("yy-MM-dd").parse(String.valueOf(medicineAddRequestDto.getStartDate()));

        Date endDate = new SimpleDateFormat("yy-MM-dd").parse(String.valueOf(medicineAddRequestDto.getEndDate()));



        List<String> dates = findDatesForWeekdaysInPeriod(
                startDate,
                endDate,
                medicineAddRequestDto.getDay()
                );


        return null;
    }


    public static List<String> findDatesForWeekdaysInPeriod(Date startDate, Date endDate, String[] weekdays) {
        List<String> result = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
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
