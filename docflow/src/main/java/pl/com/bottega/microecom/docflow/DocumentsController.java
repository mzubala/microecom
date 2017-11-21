package pl.com.bottega.microecom.docflow;

import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/documents")
public class DocumentsController {

    private CommandGateway gateway;

    private DocumentFinder finder;

    @PostMapping
    public BaseDocumentDto create(@RequestBody CreateDocumentCommand cmd) {
        Long id = gateway.execute(cmd);
        BaseDocumentDto dto = finder.find(id);
        dto.add(linkTo(methodOn(DocumentsController.class).get(id)).withRel("details"));
        return dto;
    }

    @GetMapping
    public PagedResources<BaseDocumentDto> search(DocumentSearchCriteria criteria) {

        return null;
    }

    @GetMapping("/documents/{id}")
    public DetailedDocumentDto get(@PathVariable Long id) {
        return new DetailedDocumentDto();
    }



}
