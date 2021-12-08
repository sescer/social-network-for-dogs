package com.tietoevry.socialnetworkfordogs.service

import com.tietoevry.socialnetworkfordogs.entity.Meeting
import com.tietoevry.socialnetworkfordogs.entity.MeetingDog
import com.tietoevry.socialnetworkfordogs.query.MeetingDogSearchQuery
import com.tietoevry.socialnetworkfordogs.repository.MeetingRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*

@Service
class MeetingService (
    private val repository: MeetingRepository
) {
    fun createMeeting(meeting : Meeting) : Long?{
        return repository.save(meeting).id
    }

    fun getMeeting(id : Long) : Meeting{
        return repository.findById(id).get()
    }

    fun updateMeeting(meeting: Meeting) {
        repository.save(meeting)
    }

    fun deleteMeeting(id : Long) {
        repository.delete(getMeeting(id))
    }

    fun searchMeetingDog(dogSearchQuery: MeetingDogSearchQuery): List<Meeting> {
        return repository.findAll(
            isInDateRange(dogSearchQuery.startDate, dogSearchQuery.endDate)
                .and(containsName(dogSearchQuery.name)
                )
        )
    }

    fun containsName(name: String?): Specification<Meeting> {
        return Specification<Meeting> { root, query, builder ->
            if (name != null && name.isNotBlank()) {
                builder.like(builder.lower(root.get("name")), "%${name.lowercase(Locale.getDefault())}%")
            } else {
                null
            }
        }
    }

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