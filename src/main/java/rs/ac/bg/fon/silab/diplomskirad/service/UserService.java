package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;
}
