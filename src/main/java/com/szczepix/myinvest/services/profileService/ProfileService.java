package com.szczepix.myinvest.services.profileService;

import com.szczepix.myinvest.dao.IProfileRepository;
import com.szczepix.myinvest.models.ProfileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ProfileService implements IProfileService {

    private final Logger LOG = Logger.getLogger(getClass().getName());

    private final IProfileRepository repository;
    private final ProfilesCache cache;

    @Autowired
    public ProfileService(final IProfileRepository repository, final ProfilesCache cache){
        this.repository = repository;
        this.cache = cache;
    }

    @PostConstruct
    public void init() {
        LOG.info("service " + this);
        updateCache();
    }

    private void updateCache() {
        cache.update(StreamSupport.stream(repository.findAll().spliterator(), false).map(ProfileModel::new)
                .collect(Collectors.toList()));
    }

    public ProfileModel findProfileByUserNameAndPassword(String username, String password) {
        return new ProfileModel(repository.findProfileByUserNameAndPassword(username, password));
    }

    public List<ProfileModel> getProfiles() {
        return cache.getCache();
    }
}
