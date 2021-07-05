CREATE TABLE DISEASE (
    id                  IDENTITY            NOT NULL,
    name                TEXT                NOT NULL,

    CONSTRAINT disease_pk PRIMARY KEY (id)
);

CREATE TABLE FORM (
    id                  IDENTITY            NOT NULL,
    name                TEXT                NOT NULL,

    CONSTRAINT form_pk PRIMARY KEY (id)
);

CREATE TABLE PACKAGE (
    id                  IDENTITY            NOT NULL,
    quantity            NUMERIC             NOT NULL,

    CONSTRAINT package_pk PRIMARY KEY (id)
);

CREATE TABLE PRICING (
    id                  IDENTITY            NOT NULL,
    sale_price          DECIMAL             NOT NULL,
    retail_price        DECIMAL             NOT NULL,
    total_funding       DECIMAL             NOT NULL,
    trade_price         DECIMAL             NOT NULL,
    charge_factor       DECIMAL             NOT NULL,
    refund              DECIMAL             NOT NULL,
    is_lump_sum         BOOLEAN             NOT NULL,
    is_free             BOOLEAN             NOT NULL,

    CONSTRAINT pricing_pk PRIMARY KEY (id)
);

CREATE TABLE MEDICINE (
    id                  IDENTITY    NOT NULL,
    ean                 TEXT        NOT NULL,
    name                TEXT        NOT NULL,
    form_id             NUMERIC     NOT NULL,
    pricing_id          NUMERIC     NOT NULL,
    package_id          NUMERIC     NOT NULL,
    disease_id          NUMERIC     NOT NULL,

    CONSTRAINT medicine_pk PRIMARY KEY (id),

    CONSTRAINT form_fk FOREIGN KEY (form_id)
        REFERENCES FORM (id),
    CONSTRAINT pricing_fk FOREIGN KEY (pricing_id)
        REFERENCES PRICING (id),
    CONSTRAINT package_fk FOREIGN KEY (package_id)
        REFERENCES PACKAGE (id),
    CONSTRAINT disease_fk FOREIGN KEY (disease_id)
        REFERENCES DISEASE (id)
);

CREATE TABLE DOSE (
    id                  IDENTITY            NOT NULL,
    dose                NUMERIC             NOT NULL,

    CONSTRAINT dose_pk PRIMARY KEY (id)
);

CREATE TABLE INGREDIENT (
    id                  IDENTITY            NOT NULL,
    name                TEXT                NOT NULL,

    dose_id             NUMERIC             NOT NULL,
    medicine_id         NUMERIC             NOT NULL,

    CONSTRAINT ingredient_pky PRIMARY KEY (id),

    CONSTRAINT medicine_fk FOREIGN KEY (medicine_id)
        REFERENCES MEDICINE (id),
    CONSTRAINT dose_fk FOREIGN KEY (dose_id)
        REFERENCES DOSE (id)
);
