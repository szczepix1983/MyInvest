package com.szczepix.myinvest.services.profileService;

import com.szczepix.myinvest.dao.IProfileRepository;
import com.szczepix.myinvest.entities.ProfileEntity;
import com.szczepix.myinvest.models.ProfileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ProfileService implements IProfileService {

    private final Integer ID = 1;

    private final IProfileRepository repository;
    private final ProfilesCache cache;

    @Autowired
    public ProfileService(final IProfileRepository repository, final ProfilesCache cache) {
        this.repository = repository;
        this.cache = cache;
    }

    @PostConstruct
    public void init() {
        updateCache();
    }

    private void updateCache() {
        cache.update(StreamSupport.stream(repository.findAll().spliterator(), false).map(ProfileModel::new)
                .collect(Collectors.toList()));
    }

    @Override
    public ProfileModel findProfileByUserNameAndPassword(String username, String password) {
        return new ProfileModel(repository.findProfileByUserNameAndPassword(username, password));
    }

    @Override
    public List<ProfileModel> getProfiles() {
        return cache.getCache();
    }

    @Override
    public ProfileModel getProfile() {
        if (!getProfiles().isEmpty()) {
            return getProfiles().get(0);
        }
        return createNewProfile();
    }

    @Override
    public void save(final ProfileModel model) {
        repository.save(model.getEntity());
    }

    private ProfileModel createNewProfile() {
        final ProfileEntity entity = new ProfileEntity();
        entity.setUserName("");
        entity.setFirstName("");
        entity.setLastName("");
        entity.setEmail("");
        entity.setMobile("");
        entity.setPassword("");
        return new ProfileModel(entity);
    }
}
