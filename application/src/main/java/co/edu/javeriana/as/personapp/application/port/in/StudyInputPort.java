package co.edu.javeriana.as.personapp.application.port.in;

import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;

import java.util.List;

public interface StudyInputPort {

    void setPersistence(StudyOutputPort studyPersistence);

    public Study create(Study study);

    public Study edit(Integer personId, Integer professionId, Study study) throws NoExistException;

    public Boolean drop(Integer personId, Integer professionId) throws NoExistException;

    public List<Study> findAll();

    public Study findOne(Integer personId, Integer professionId) throws NoExistException;

    public Integer count();
}