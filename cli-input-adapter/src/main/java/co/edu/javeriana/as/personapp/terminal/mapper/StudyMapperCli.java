package co.edu.javeriana.as.personapp.terminal.mapper;

import org.springframework.stereotype.Component;

import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.terminal.model.StudyModelCli;

@Component
public class StudyMapperCli {

    public StudyModelCli fromDomainToAdapterCli(Study study) {
        StudyModelCli studyModelCli = new StudyModelCli();
        studyModelCli.setPersonId(study.getPerson().getIdentification());
        studyModelCli.setProfessionId(study.getProfession().getIdentification());
        studyModelCli.setGraduationDate(study.getGraduationDate());
        studyModelCli.setUniversity(study.getUniversityName());
        return studyModelCli;
    }
}
