package corentinf.testagregio.adapter;

public interface AbstractDtoDomainAdapter<Dto, Domain>{
    Dto fromDomToDto(Domain domain);

    Domain fromDtoToDom(Dto dto);
}
