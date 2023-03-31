
CREATE TABLE public.entidade (
    id_entidade bigint NOT NULL,
    cnpj character varying(20),
    codigo integer,
    esfera integer,
    nome character varying(255),
    poder integer,
    id_entidade_brasao bigint,
    codigo_municipio integer,
    tipo_entidade character varying(64) NOT NULL,
    mensageria_token character varying(255),
    mensageria_id_cliente character varying(255),
    nome_municipio character varying(255),
    CONSTRAINT ocn_entidade_tipo_entidade_check CHECK (((tipo_entidade)::text = ANY (ARRAY[('PREFEITURA'::character varying)::text, ('CAMARA_MUNICIPAL'::character varying)::text, ('FUNDO_ESPECIAL'::character varying)::text, ('AUTARQUIA'::character varying)::text, ('FUNDACAO_PUBLICA'::character varying)::text, ('EMPRESA_PUBLICA_NAO_DEPENDENTE'::character varying)::text, ('SOCIEDADE_ECONOMIA_MISTA_NAO_DEPENDENTE'::character varying)::text, ('EMPRESA_PUBLICA_DEPENDENTE'::character varying)::text, ('SOCIEDADE_ECONOMIA_MISTA_DEPENDENTE'::character varying)::text, ('RPPS'::character varying)::text])))
);

ALTER TABLE ONLY public.entidade
    ADD CONSTRAINT mfc_entidade_pkey PRIMARY KEY (id_entidade);


CREATE TABLE public.entidade_brasao (
    id_entidade_brasao bigint NOT NULL,
    brasao bytea,
    mime_type character varying(100) NOT NULL
);

ALTER TABLE ONLY public.entidade_brasao
    ADD CONSTRAINT infra_entidade_brasao_pkey PRIMARY KEY (id_entidade_brasao);


CREATE TABLE public.fonte_recurso (
    id_fonte_recurso bigserial NOT NULL,
    codigo character(2),
    descricao character varying(255),
    fim_vigencia timestamp without time zone,
    inicio_vigencia timestamp without time zone,
    situacao character varying(10),
    tipo character varying(20) NOT NULL,
    CONSTRAINT mfc_fonte_recurso_situacao_check CHECK (((situacao)::text = ANY (ARRAY[('ATIVO'::character varying(10))::text, ('INATIVO'::character varying(10))::text]))),
    CONSTRAINT mfc_fonte_recurso_tipo_check CHECK (((tipo)::text = ANY (ARRAY[('ORCAMENTARIA'::character varying(20))::text, ('ESPECIAL'::character varying(20))::text, ('EXTRAORDINARIA'::character varying(20))::text])))
);

ALTER TABLE ONLY public.fonte_recurso
    ADD CONSTRAINT mfc_fonte_recurso_pkey PRIMARY KEY (id_fonte_recurso);



INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('01', 'Tesouro', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');
INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('02', 'Transferências e Convênios Estaduais - Vinculados', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');
INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('03', 'Recursos Próprios de Fundos Especiais de Despesa - Vinculados', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');
INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('04', 'Recursos Próprios da Administração Indireta', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');
INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('05', 'Transferências e Convênios Federais - Vinculados', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');
INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('06', 'Outras Fontes de Recursos', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');
INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('07', 'Operações de Crédito', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');
INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('91', 'Tesouro - Exercícios Anteriores', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');
INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('92', 'Transferências e Convênios Estaduais - Vinculados - Exercícios Anteriores', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');
INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('93', 'Recursos Próprios de Fundos Especiais de Despesa - Vinculados - Exercícios Anteriores', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');
INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('94', 'Recursos Próprios da Administração Indireta - Exercícios Anteriores', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');
INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('95', 'Transferências e Convênios Federais - Vinculados - Exercícios Anteriores', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');
INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('96', 'Outras Fontes de Recursos - Exercícios Anteriores', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');
INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('97', 'Operações de Crédito - Exercícios Anteriores', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');
INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('98', 'Emendas Parlamentares Individuais - Exercícios Anteriores', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');
INSERT INTO public.fonte_recurso
(codigo, descricao, fim_vigencia, inicio_vigencia, situacao, tipo)
VALUES('08', 'Emendas Parlamentares Individuais', NULL, '2009-01-01 00:00:00.000', 'ATIVO', 'ORCAMENTARIA');



