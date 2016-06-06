package com.mfs.services

import grails.transaction.Transactional
import com.mfs.entities.*
import com.mfs.policies.*

@Transactional
class PoliciesService {

    @Transactional(readOnly = true)
    def assignMemberCharges(Organization org, Member member) {

    }
}
