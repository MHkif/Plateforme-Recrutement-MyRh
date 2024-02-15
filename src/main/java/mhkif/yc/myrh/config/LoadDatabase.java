package mhkif.yc.myrh.config;


import jakarta.persistence.EntityNotFoundException;
import mhkif.yc.myrh.dto.requests.*;
import mhkif.yc.myrh.enums.JobApplicationStatus;
import mhkif.yc.myrh.enums.StudyLevel;
import mhkif.yc.myrh.enums.UserStatus;
import mhkif.yc.myrh.model.*;
import mhkif.yc.myrh.repository.*;
import mhkif.yc.myrh.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final JobApplicantRepo jobApplicantRepo;

    public LoadDatabase(JobApplicantRepo jobApplicantRepo) {
        this.jobApplicantRepo = jobApplicantRepo;
    }


    @Bean
    CommandLineRunner initDatabase(
            ICompanyService companyService,
            IAdminService adminService,
            IActivityAreaService activityAreaService,
            ICityService cityService,
            IOfferService offerService,
            JobSeekerRepo jobSeekerRepo,
            ProfileRepo profileRepo,
            IJobSeekerService jobSeekerService,
            QuestionRepo questionRepo,
            AnswerRepo answerRepo) {

        return args -> {


            AdminReq admin = new AdminReq();
            admin.setFirst_name("Abdelmalek");
            admin.setLast_name("Achkif");
            admin.setEmail("admin@myrh.com");
            admin.setPassword("aqwzsxedc");


            CompanyReq c1 = new CompanyReq();
            c1.setName("Sofrecom");
            c1.setEmail("sofrecom1@orange.com");
            c1.setPassword("12345678");
            c1.setImage("sofrecom.png");


            CityReq city = new CityReq();
            city.setName("Casablanca");

            CityReq city2 = new CityReq();
            city2.setName("Nador");

            CityReq city3 = new CityReq();
            city3.setName("Rabat");

            ActivityAreaReq activityAreaReq = new ActivityAreaReq();
            activityAreaReq.setDescription("Information Technology");

            OfferReq offer = new OfferReq();
            offer.setTitle("Developpeur / Developpeuse Full stack");
            offer.setDescription("EYSI, entreprise de développement informatique, cherche un stagiaire à" +
                    "partir de BAC +2 pour une durée variant entre 2 à 6 mois.");
            offer.setCompany(Company.builder().id(1).build());
            offer.setCity(City.builder().id(1).build());
            offer.setProfile(ActivityArea.builder().id(1).build());
            offer.setLevel(StudyLevel.BacPlus2);
            offer.setSalary(12000);

            Profile profile = new Profile();
            profile.setName("Java");
            profile.setActivityArea(ActivityArea.builder().id(1).build());


            JobSeekerReq jobSeeker = new JobSeekerReq();
            jobSeeker.setFirst_name("salah");
            jobSeeker.setLast_name("hydra");
            jobSeeker.setEmail("salah@myrh.com");
            jobSeeker.setPassword("111salah");
            jobSeeker.setProfile(profile);
            jobSeeker.setProfile_verify(false);


            log.info("Preloading Admin 1 : " + adminService.create(admin).toString());
            log.info("Preloading Company 1: " + companyService.create(c1).toString());
            log.info("Preloading City 1 : " + cityService.create(city).toString());
            log.info("Preloading City 2 : " + cityService.create(city2).toString());
            log.info("Preloading City 3 : " + cityService.create(city3).toString());

            log.info("Preloading ActivityArea 1 : " + activityAreaService.create(activityAreaReq).toString());
            log.info("Preloading Profile 1 : " + profileRepo.save(profile));
            log.info("Preloading Offer 1 : " + offerService.create(offer).toString());
            log.info("Preloading JobSeeker 1 : " + jobSeekerService.create(jobSeeker).toString());
            saveFakeJobApplication();

            createJavaQuiz(profileRepo, questionRepo, answerRepo);

        };
    }


    private void saveFakeJobApplication() {
        JobApplicantId jobApplicantId = new JobApplicantId();
        jobApplicantId.setJobSeeker_id(1);
        jobApplicantId.setOffer_id(1);
        JobApplicant jobApplicant = new JobApplicant();
        jobApplicant.setId(jobApplicantId);
        jobApplicant.setResume("path/to/resume");
        jobApplicant.setStatus(JobApplicationStatus.ACCEPTED);
        jobApplicant.setIsViewed(true);
    }

    private void createJavaQuiz(ProfileRepo profileRepo, QuestionRepo questionRepo, AnswerRepo answerRepo) {
        Profile profile = profileRepo.findById(1).orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        Question question1 = new Question();
        question1.setQuestion("What is a correct syntax to output \"Hello World\" in Java?");
        question1.setProfile(profile);
        var savedQ1 = questionRepo.save(question1);


        Answer answer1Q1 = new Answer();
        answer1Q1.setQuestion(savedQ1);
        answer1Q1.setAnswer("System.out.println(\"Hello World\")");
        answer1Q1.setRightAnswer(true);
        answerRepo.save(answer1Q1);

        Answer answer2Q1 = new Answer();
        answer2Q1.setQuestion(savedQ1);
        answer2Q1.setAnswer("Console.WriteLine(\"Hello World\")");
        answer2Q1.setRightAnswer(false);
        answerRepo.save(answer2Q1);


        Answer answer3Q1 = new Answer();
        answer3Q1.setQuestion(savedQ1);
        answer3Q1.setAnswer("print(\"Hello World\")");
        answer3Q1.setRightAnswer(false);
        answerRepo.save(answer3Q1);


        Answer answer4Q1 = new Answer();
        answer4Q1.setQuestion(savedQ1);
        answer4Q1.setAnswer("echo(\"Hello World\")");
        answer4Q1.setRightAnswer(false);
        answerRepo.save(answer4Q1);


        // ===== Question 2 =======

        Question question2 = new Question();
        question2.setQuestion("Java is short for \"JavaScript\".");
        question2.setProfile(profile);
        var savedQ2 = questionRepo.save(question2);

        Answer answer1Q2 = new Answer();
        answer1Q2.setQuestion(savedQ2);
        answer1Q2.setAnswer("False");
        answer1Q2.setRightAnswer(false);
        answerRepo.save(answer1Q2);


        Answer answer2Q2 = new Answer();
        answer2Q2.setQuestion(savedQ2);
        answer2Q2.setAnswer("True");
        answer2Q2.setRightAnswer(true);
        answerRepo.save(answer2Q2);

        // ===== Question 3 ====

        Question question3 = new Question();
        question3.setQuestion("Which data type is used to create a variable that should store text?");
        question3.setProfile(profile);
        var savedQ3 =  questionRepo.save(question3);

        Answer answer1Q3 = new Answer();
        answer1Q3.setQuestion(savedQ3);
        answer1Q3.setAnswer("string");
        answer1Q3.setRightAnswer(false);
        answerRepo.save(answer1Q3);

        Answer answer2Q3 = new Answer();
        answer2Q3.setQuestion(savedQ3);
        answer2Q3.setAnswer("String");
        answer2Q3.setRightAnswer(true);
        answerRepo.save(answer2Q3);

        Answer answer3Q3 = new Answer();
        answer3Q3.setQuestion(savedQ3);
        answer3Q3.setAnswer("Txt");
        answer3Q3.setRightAnswer(false);
        answerRepo.save(answer3Q3);

        Answer answer4Q3 = new Answer();
        answer4Q3.setQuestion(savedQ3);
        answer4Q3.setAnswer("myString");
        answer4Q3.setRightAnswer(false);
        answerRepo.save(answer4Q3);

        // ===== Question 4 ====

        Question question4 = new Question();
        question4.setQuestion("How do you create a variable with the floating number 2.8?");
        question4.setProfile(profile);
        var savedQ4 =questionRepo.save(question4);


        Answer answer1Q4 = new Answer();
        answer1Q4.setQuestion(savedQ4);
        answer1Q4.setAnswer("x = 2.8f;");
        answer1Q4.setRightAnswer(false);
        answerRepo.save(answer1Q4);

        Answer answer2Q4 = new Answer();
        answer2Q4.setQuestion(savedQ4);
        answer2Q4.setAnswer("byte x = 2.8f");
        answer2Q4.setRightAnswer(false);
        answerRepo.save(answer2Q4);

        Answer answer3Q4 = new Answer();
        answer3Q4.setQuestion(savedQ4);
        answer3Q4.setAnswer("float x = 2.8;");
        answer3Q4.setRightAnswer(true);
        answerRepo.save(answer3Q4);


        Answer answer4Q4 = new Answer();
        answer4Q4.setQuestion(savedQ4);
        answer4Q4.setAnswer("int x = 2.8f;");
        answer4Q4.setRightAnswer(false);
        answerRepo.save(answer4Q4);

        // ===== Question 5 ====

        Question question5 = new Question();
        question5.setQuestion("The value of a string variable can be surrounded by single quotes.");
        question5.setProfile(profile);
        var savedQ5 = questionRepo.save(question5);

        Answer answer1Q5 = new Answer();
        answer1Q5.setQuestion(savedQ5);
        answer1Q5.setAnswer("TRUE");
        answer1Q5.setRightAnswer(false);
        answerRepo.save(answer1Q5);


        Answer answer2Q5 = new Answer();
        answer2Q5.setQuestion(savedQ5);
        answer2Q5.setAnswer("FALSE");
        answer2Q5.setRightAnswer(true);
        answerRepo.save(answer2Q5);


        // ===== Question 6 ====

        Question question6 = new Question();
        question6.setQuestion("In Java, it is possible to inherit attributes and methods from one class to another.");
        question6.setProfile(profile);
        var savedQ6 = questionRepo.save(question6);


        Answer answer1Q6 = new Answer();
        answer1Q6.setQuestion(savedQ6);
        answer1Q6.setAnswer("TRUE");
        answer1Q6.setRightAnswer(true);
        answerRepo.save(answer1Q6);


        Answer answer2Q6 = new Answer();
        answer2Q6.setQuestion(savedQ6);
        answer2Q6.setAnswer("FALSE");
        answer2Q6.setRightAnswer(false);
        answerRepo.save(answer2Q6);

        // ===== Question 7 ====

        Question question7 = new Question();
        question7.setQuestion("Which keyword is used to return a value inside a method?");
        question7.setProfile(profile);
        var savedQ7 = questionRepo.save(question7);


        Answer answer1Q7 = new Answer();
        answer1Q7.setQuestion(savedQ7);
        answer1Q7.setAnswer("get");
        answer1Q7.setRightAnswer(false);
        answerRepo.save(answer1Q7);

        Answer answer2Q7 = new Answer();
        answer2Q7.setQuestion(savedQ7);
        answer2Q7.setAnswer("return");
        answer2Q7.setRightAnswer(true);
        answerRepo.save(answer2Q7);


        Answer answer3Q7 = new Answer();
        answer3Q7.setQuestion(savedQ7);
        answer3Q7.setAnswer("break");
        answer3Q7.setRightAnswer(false);
        answerRepo.save(answer3Q7);

        Answer answer4Q7 = new Answer();
        answer4Q7.setQuestion(savedQ7);
        answer4Q7.setAnswer("void");
        answer4Q7.setRightAnswer(false);
        answerRepo.save(answer4Q7);


    }
}