package com.example.demo.models.services;

import com.example.demo.models.dao.IUsuarioDao;
import com.example.demo.models.entities.Usuario;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Repository
public class UsuarioService implements IUsuarioService {

    private final IUsuarioDao dao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(IUsuarioDao dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void save(Usuario usuario) {
        usuario.setPassword(this.passwordEncoder.encode(usuario.getPassword()));
        dao.save(usuario);
    }

    @Override
    public Usuario findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Usuario findByName(String name) {
        return dao.findByUsername(name);
    }

    @Override
    public List<Usuario> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        dao.deleteById(id);
    }

}

