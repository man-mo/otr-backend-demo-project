package com.daimler.otr.demo.account.builder;

import com.daimler.otr.demo.account.model.entities.VisitCode;
import com.daimler.otr.demo.account.utils.SpringApplicationContext;
import com.daimler.otr.demo.account.reporitories.VisitCodeRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VisitCodeBuilder {
    private VisitCode visitCode = new VisitCode();

    public static VisitCodeBuilder withDefault() {
        return new VisitCodeBuilder().withId(1)
                                     .withValue("osiueowiure")
                                     .withSalesmanId(1);
    }

    public VisitCode build() {
        return visitCode;
    }

    public VisitCode persist() {
        VisitCodeRepository repository = SpringApplicationContext.getBean(VisitCodeRepository.class);
        return repository.saveAndFlush(visitCode);
    }

    public VisitCodeBuilder withId(Integer id) {
        visitCode.setId(id);
        return this;
    }

    public VisitCodeBuilder withSalesmanId(Integer salesmanId) {
        visitCode.setSalesmanId(salesmanId);
        return this;
    }

    public VisitCodeBuilder withValue(String value) {
        visitCode.setValue(value);
        return this;
    }
}
