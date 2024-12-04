package com.microservices.job.job;
import com.microservices.job.job.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @GetMapping()
    public ResponseEntity<List<JobDTO>> getalljobs(){
        return new ResponseEntity<>(jobService.getalljobs(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getjobyid(@PathVariable Long id){
            JobDTO jobDTO = jobService.getjobbyid(id);
            if(jobDTO != null) {
                return new ResponseEntity<>(jobDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<String> create(@RequestBody Job job){
        jobService.create(job);
        return new ResponseEntity<>( "created sucessfully",HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
      boolean deleted = jobService.delete(id);
         if (deleted)
             return new ResponseEntity<>("deleted sucessfully", HttpStatus.OK);
         return new ResponseEntity<>("ID not found",HttpStatus.NOT_FOUND);
    }


    @PostMapping("/post")
    public ResponseEntity<String>   createed(@RequestBody Job job1){
        jobService.created(job1);
        return new ResponseEntity<>("created",HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,@RequestBody Job job){
         boolean updated = jobService.updatejob(id,job);
         if(updated) {
             return new ResponseEntity<>("updated sucessfully", HttpStatus.OK);
         }
         return new ResponseEntity<>("ID not found",HttpStatus.NOT_FOUND);

    }



}
