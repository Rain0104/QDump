package org.dataart.qdump.service.resources;

import javax.ws.rs.Path;

import org.dataart.qdump.service.ServiceQdump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("/person")
@Component
public class PersonEntityResource {
	@Autowired
	private ServiceQdump serviceQdump;
}
