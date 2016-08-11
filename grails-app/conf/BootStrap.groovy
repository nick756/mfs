import com.mfs.entities.*

class BootStrap {

    def init = { servletContext ->
        
        if(UserRole.list().size() == 0) {
            new UserRole(code: 10, name_EN: 'System Administrator', name_MS: 'Pentadbir Sistem').save(flush: true)
            new UserRole(code: 20, name_EN: 'Organization Administrator', name_MS: 'Pentadbir Organisasi').save(flush: true)
            new UserRole(code: 30, name_EN: 'Data Entry Operator', name_MS: 'Operator Kemasukan Data').save(flush: true)
            new UserRole(code: 40, name_EN: 'Processing Officer', name_MS: 'Pegawai Pemprosesan', maskMenu: 960).save(flush: true)
            new UserRole(code: 50, name_EN: 'Financial Officer', name_MS: 'Pegawai Kewangan').save(flush: true)
        }
        
        if(Relationship.list().size() == 0) {
            new Relationship(code: 10, value_EN: 'Spouse', value_MS: 'Spouse').save(flush: true)
            new Relationship(code: 15, value_EN: 'Parent', value_MS: 'Parent').save(flush: true)
            new Relationship(code: 20, value_EN: 'Children', value_MS: 'Children').save(flush: true)
            new Relationship(code: 30, value_EN: 'Siblings', value_MS: 'Siblings').save(flush: true)
            new Relationship(code: 35, value_EN: 'Friend', value_MS: 'Friend').save(flush: true)
            new Relationship(code: 40, value_EN: 'Collegue', value_MS: 'Collegue').save(flush: true)
        }
        
        if(MemberStatus.list().size() == 0) {
            println ''
            println 'Generating instances of MemberStatus ...'
            
            new MemberStatus(code: 10, value_EN: 'Pending Approval', value_MS: 'Menunggu Kelulusan').save(flush: true)
            new MemberStatus(code: 20, value_EN: 'Approved', value_MS: 'Diluluskan').save(flush: true)
            new MemberStatus(code: 30, value_EN: 'Rejected', value_MS: 'Ditolak').save(flush: true)
            new MemberStatus(code: 40, value_EN: 'Retired', value_MS: 'Bersara').save(flush: true)
            new MemberStatus(code: 50, value_EN: 'Deceased', value_MS: 'Meninggal Dunia').save(flush: true)
            
            println "... total instances created: ${MemberStatus.list().size()}"
        }
        
        if(MaritalStatus.list().size() == 0) {
            println ''
            println 'Generating instances of MaritalStatus...'
            
            new MaritalStatus(code: 1, value_EN: 'Single', value_MS: 'Tunggal').save(flush: true)
            new MaritalStatus(code: 2, value_EN: 'Engaged', value_MS: 'Terlibat').save(flush: true)
            new MaritalStatus(code: 3, value_EN: 'Married', value_MS: 'Telah Berkahwin').save(flush: true)
            new MaritalStatus(code: 4, value_EN: 'Divorsed', value_MS: 'Tunggal').save(flush: true)
            new MaritalStatus(code: 5, value_EN: 'Widow/Widower', value_MS: 'Janda/Duda').save(flush: true)
            
            println "... total instances created: ${MaritalStatus.list().size()}"
        }
        
        if(State.list().size() == 0) {
            
            //  No multiple languages are required for list of States,
            //  Codes follow ISO 3166-2:MY standard
            
            println ''
            println 'Generating instances of State...'
            
            new State(code: 1, name: 'Johor').save(flush: true)
            new State(code: 2, name: 'Kedah').save(flush: true)
            new State(code: 3, name: 'Kelantan').save(flush: true)
            new State(code: 4, name: 'Melaka').save(flush: true)
            new State(code: 5, name: 'Negeri Sembilan').save(flush: true)
            new State(code: 6, name: 'Pahang').save(flush: true)
            new State(code: 7, name: 'Pulau Pinang').save(flush: true)
            new State(code: 8, name: 'Perak').save(flush: true)
            new State(code: 9, name: 'Perlis').save(flush: true)
            new State(code: 10, name: 'Selangor').save(flush: true)
            new State(code: 11, name: 'Terengganu').save(flush: true)
            new State(code: 12, name: 'Sabah').save(flush: true)
            new State(code: 13, name: 'Sarawak').save(flush: true)
            new State(code: 14, name: 'Wilayah Persekutuan Kuala Lumpur').save(flush: true)
            new State(code: 15, name: 'Wilayah Persekutuan Labuan').save(flush: true)
            new State(code: 16, name: 'Wilayah Persekutuan Putrajaya').save(flush: true) 
            
            println "... total instances created: ${State.list().size()}"
        }
        
        if(MembershipType.list().size() == 0) {
            println ''
            println 'Generating instances of MembershipType...'
            
            new MembershipType(code: 10, value_EN: 'Benefit', value_MY: 'Manfaat').save(flush: true)
            new MembershipType(code: 20, value_EN: 'Loan', value_MY: 'Pinjaman').save(flush: true)
            new MembershipType(code: 30, value_EN: 'Associate Member', value_MY: 'Ahli Bersekutu').save(flush: true)
            
            println "... total instances created: ${MembershipType.list().size()}"
        }
        
        if(EmploymentSector.list().size() == 0) {
            println ''
            println 'Generating list of instances for Employment Sector...'
            
            new EmploymentSector(code: 10, value_EN: 'GOVERNMENT', value_MS: 'GOVERNMENT').save(flush: true)
            new EmploymentSector(code: 20, value_EN: 'GOVERNMENT LINKED', value_MS: 'GOVERNMENT LINKED').save(flush: true)
            new EmploymentSector(code: 30, value_EN: 'PRIVATE', value_MS: 'PRIVATE').save(flush: true)
            new EmploymentSector(code: 40, value_EN: 'POLICE', value_MS: 'POLICE').save(flush: true)
            
            println "... total Records created: ${EmploymentSector.list().size()}"
        }
        
        if(EmploymentType.list().size() == 0) {
            println ''
            println "Populating Employment Type Table ..."
            
            new EmploymentType(code: 10, value_EN: 'PERMANENT', value_MS: "PERMANENT").save(flush: true)
            new EmploymentType(code: 20, value_EN: 'TEMPORARY', value_MS: "TEMPORARY").save(flush: true)
            new EmploymentType(code: 30, value_EN: 'CONTRACT', value_MS: "CONTRACT").save(flush: true)
            
            println "... total Records created: ${EmploymentType.list().size()}"
        }
        
        if(Organization.list().size() == 0) {
            
            def org = new Organization(
                code: 1,
                name: 'Superior Cooperators Bhd',
                registrationNo: 'CT-0001'
            )
            
            org.save(flush: true)
            
            def generator = new MemberNumberGenerator(
                serialNumber: 0,
                effectiveDate: new Date(),
                organization: org
            )
            
            if(!generator.save()) {
                println "\nError while saving MemberNumberGenerator:\n${generator.errors.allErrors.join('\n')}"
            }
            else {
                println "Number Generator created: ${generator?.serialNumber}"
            }  
            
            if(generator) {
                org.numberGenerator = generator
                org.save()
            }
            
            def branch = new Branch(
                code: 0,
                name: 'Head Quarters',
                city: 'Kuala Lumpur',
                organization: org
            )
            
            def branch2 = new Branch(
                code: 1,
                name: 'South Branch',
                city: 'Johor Baru',
                organization: org            
            ).save()
            
            if(!branch.save(flush: true)) {
                branch.errors.allErrors.join("\n")
            }
            
            org.addToBranches(branch)
            org.addToBranches(branch2)
            org.save()
            
            println "Organization created: ${org.name}/${org.branches}"
        }
        
        if(User.list().size() == 0) {
            def org = Organization.findByCode(1)
            def branches = org.branches.asList()
            def branch
            
            branches.each {
                if(it.code == 0) {
                    branch = it
                }
            }
            def user = new User(
                name:           'Nikolay',
                login:          'nick',
                passw:          '1234',
                role:           UserRole.findByCode(40),
                organization:   org,
                branch:         branch,
            )
            
            user.validate()
            
            if(!user.save()) {
                println user.errors.allErrors.join('\n')
            }
            
            println "Default User was created: ${user?.name}/${user?.role?.name_EN}"
            println "${User.list()}"
        }
        
        //  Populating list of Genders
        if(Gender.list().size() == 0) {
            println ''
            println 'Generating instances of Gender...'
            
            new Gender(code: 1, value_EN: 'Male', value_MS: 'Lelaki').save(flush: true)
            new Gender(code: 2, value_EN: 'Female', value_MS: 'Perempuan').save(flush: true)
            
            println "... instances created: ${Gender.list().size()}"
            
        }
    }
    
    def destroy = {
    }
}
