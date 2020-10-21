package com.dvlcube.app.rest;

import static com.dvlcube.app.manager.data.e.Menu.CONFIGURATION;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dvlcube.app.interfaces.MenuItem;
import com.dvlcube.app.jpa.repo.JobRepository;
import com.dvlcube.app.manager.data.JobBean;
import com.dvlcube.app.manager.data.SkillBean;
import com.dvlcube.app.manager.data.vo.MxRestResponse;
import com.dvlcube.utils.interfaces.rest.MxFilterableBeanService;

@RestController
@MenuItem(value = CONFIGURATION)
@RequestMapping("${dvl.rest.prefix}/jobs")
public class JobService implements MxFilterableBeanService<JobBean, Long> {
	@Autowired
	private JobRepository repo;
	@Override
	@GetMapping
	public Iterable<JobBean> get(@RequestParam Map<String, String> params) {
		System.out.println("AQUI");
		return repo.firstPage();
	}

	@PostMapping
	public MxRestResponse post(@Valid @RequestBody JobBean body) {
		JobBean save = repo.save(body);
		return GenericRestResponse.ok(save.getId());
	}

	@GetMapping("/like")
	public Iterable<JobBean> getLike(@RequestParam(required = true) String name) {
		return repo.findAllLike(name);
	}


	@Override
	@GetMapping("/{id}")
	public Optional<JobBean> get(@PathVariable Long id) {
		return repo.findById(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repo.deleteById(id);
	}


}
