package co.edu.javeriana.as.personapp.application.usecase;

import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.UseCase;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Study;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Slf4j
@UseCase
public class StudyUseCase implements StudyInputPort {

    private StudyOutputPort studyPersistence;

    public StudyUseCase(@Qualifier("studyOutputAdapterMaria") StudyOutputPort studyPersistence){
        this.studyPersistence = studyPersistence;
    }

    public void setStudyPersistence(StudyOutputPort studyPersistence) {
        this.studyPersistence = studyPersistence;
    }

    @Override
    public void setPersistence(StudyOutputPort studyPersistence) {

    }

    public Study create(Study study){
        log.debug("Into create on application domain");
        return studyPersistence.save(study);
    }

    public Study edit(Integer personId, Integer professionId, Study study) throws NoExistException {
        Study oldStudy = studyPersistence.findById(personId, professionId);
        if(oldStudy != null){
            return studyPersistence.save(study);
        }
        throw new NoExistException("the study with the persion id " + personId
                + " and the profession id "+ professionId + "does not exist into db, cannot be edited");
    }

    public Boolean drop(Integer personId, Integer professionId)throws NoExistException{
        Study oldStudy = studyPersistence.findById(personId, professionId);
        if(oldStudy != null){
            return studyPersistence.delete(personId, professionId);
        }
        throw new NoExistException("the study with the persion id " + personId
                + " and the profession id "+ professionId + "does not exist into db, cannot be edited, cannot be dropped");
    }

    public List<Study> findAll(){
        log.info("Output: "+studyPersistence.getClass());
        return  studyPersistence.find();
    }

    public Study findOne(Integer personId, Integer professionId) throws NoExistException {
        Study study = studyPersistence.findById(personId, professionId);
        if (study != null)
            return study;
        throw new NoExistException(
                "The study with person id " + personId + " and profession id " + professionId
                        + "does not exist into db, cannot be found");
    }

    @Override
    public Integer count() {
        return findAll().size();
    }


}