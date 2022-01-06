package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.Meeting
import com.tietoevry.socialnetworkfordogs.query.MeetingDogSearchQuery
import com.tietoevry.socialnetworkfordogs.repository.MeetingRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*

/**
 * Class represents CRUD and searching of meetings
 */
@Service
class MeetingService (
    private val repository: MeetingRepository
) {
    /**
     * Method for creating meeting
     *
     * @param meeting - meeting information to create
     * @return - meeting id
     */
    fun createMeeting(meeting : Meeting) : Long?{
        return repository.save(meeting).id
    }
    /**
     * Method for getting meeting by id
     *
     * @param id - meeting id
     * @return - meeting information
     */
    fun getMeeting(id : Long) : Meeting{
        return repository.findById(id).get()
    }
    /**
     * Method for updating meeting info
     *
     * @param meeting - meeting info to update
     */
    fun updateMeeting(meeting: Meeting) {
        repository.save(meeting)
    }
    /**
     * Method for deleting meeting by id
     *
     * @param id - meeting id
     */
    fun deleteMeeting(id : Long) {
        repository.delete(getMeeting(id))
    }
    /**
     * Method for searching dogs
     *
     * @param dogSearchQuery - searching query(by meeting's name/date range)
     * @return - list of found meetings
     */
    fun searchMeetingDog(dogSearchQuery: MeetingDogSearchQuery): List<Meeting> {
        return repository.findAll(
            isInDateRange(dogSearchQuery.startDate, dogSearchQuery.endDate)
                .and(containsName(dogSearchQuery.name)
                )
        )
    }
    /**
     * Method for creating query to db for searching dogs by meetings's name
     *
     * @param name - meeting's name to search
     * @return - specification of found meeting
     */
    fun containsName(name: String?): Specification<Meeting> {
        return Specification<Meeting> { root, query, builder ->
            if (name != null && name.isNotBlank()) {
                builder.like(builder.lower(root.get("name")), "%${name.lowercase(Locale.getDefault())}%")
            } else {
                null
            }
        }
    }
    /**
     * Method for creating query to db for searching dogs in date range
     *
     * @param startDate - start of date range to search
     * @param endDate - end of date range to search
     *
     * @return - specification of found meeting
     */
    fun isInDateRange(startDate: Date?, endDate: Date?): Specification<Meeting> {
        return Specification<Meeting> { root, query, builder ->
            if (startDate == null && endDate == null)
                null
            else if (startDate == null)
                builder.lessThanOrEqualTo(root.get("meetingDate"), endDate!!)
            else if (endDate == null)
                builder.greaterThanOrEqualTo(root.get("meetingDate"), startDate)
            else
                builder.between(root.get("meetingDate"), startDate, endDate)
        }
    }



}