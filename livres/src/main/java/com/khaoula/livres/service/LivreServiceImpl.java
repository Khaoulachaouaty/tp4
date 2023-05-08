package com.khaoula.livres.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.khaoula.livres.dto.LivreDTO;
import com.khaoula.livres.entities.Editeur;
import com.khaoula.livres.entities.Livre;
import com.khaoula.livres.repos.EditeurRepository;
import com.khaoula.livres.repos.LivreRepository;
@Service
public class LivreServiceImpl implements LivreService{
	
	@Autowired
	LivreRepository livreRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	EditeurRepository editeurRepository;

	@Override
	public LivreDTO saveLivre(LivreDTO l) {
		return convertEntityToDto(livreRepository.save(convertDtoToEntity(l)));
	}

	@Override
	public LivreDTO updateLivre(LivreDTO l) {
		return convertEntityToDto(livreRepository.save(convertDtoToEntity(l)));
		
	}

	@Override
	public void deleteLivre(Livre l) {
		livreRepository.delete(l);

	}

	@Override
	public void deleteLivreById(Long id) {
		livreRepository.deleteById(id);
	}

	@Override
	public LivreDTO getLivre(Long id) {
		return convertEntityToDto(livreRepository.findById(id).get());
	}

	@Override
	public List<LivreDTO> getAllLivres() {
		return livreRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());	}
	@Override
	public Page<Livre> getAllLivresParPage(int page, int size) {
	return livreRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public List<Livre> findByNomLivre(String nom) {
		return livreRepository.findByNomLivre(nom);
	}

	@Override
	public List<Livre> findByNomLivreContains(String nom) {
		return livreRepository.findByNomLivreContains(nom);
	}

	@Override
	public List<Livre> findByNomPrix(String nom, Double prix) {
		return livreRepository.findByNomPrix(nom,prix);
	}

	@Override
	public List<Livre> findByEditeur(Editeur editeur) {
		return livreRepository.findByEditeur(editeur);
	}

	@Override
	public List<Livre> findByEditeurIdEd(Long id) {
		return livreRepository.findByEditeurIdEd(id);
	}
	
	@Override
	public List<Livre> findByOrderByNomLivreAsc() {
		return livreRepository.findByOrderByNomLivreAsc();
	}

	@Override
	public List<Livre> trierLivresNomsPrix() {
		return livreRepository.trierLivresNomsPrix();
	}

	public List<Editeur> getAllEditeurs() {
		
		return editeurRepository.findAll();
	}

 	@Override
	public LivreDTO convertEntityToDto(Livre livre) {
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
 			LivreDTO livreDTO = modelMapper.map(livre, LivreDTO.class);
 			 return livreDTO;
 			 }

 		
 		
 		
		 	   /* return LivreDTO.builder()
		 	        .idLivre(l.getIdLivre())
		 	        .nomLivre(l.getNomLivre())
		 	        //.prixLivre(l.getPrixLivre())
		 	        .datePublication(l.getDatePublication())
		 	        .editeur(l.getEditeur())
		 	        //.nomEd(l.getEditeur().getNomEd())
		 	        .build();*/
	

	@Override
	public Livre convertDtoToEntity(LivreDTO livreDto) {
		Livre livre =new Livre();
		livre = modelMapper.map(livreDto, Livre.class);
		/*livre.setIdLivre(livreDto.getIdLivre());
		livre.setNomLivre(livreDto.getNomLivre());
		livre.setPrixLivre(livreDto.getPrixLivre());
		livre.setDatePublication(livreDto.getDatePublication());
		livre.setEditeur(livreDto.getEditeur());*/
		return livre;
	}
	

	
}
