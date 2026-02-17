CREATE TABLE participante (
                              id BIGSERIAL PRIMARY KEY,
                              nome VARCHAR(255) NOT NULL,
                              email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE evento (
                        id BIGSERIAL PRIMARY KEY,
                        nome VARCHAR(255) NOT NULL,
                        data TIMESTAMP NOT NULL,
                        local VARCHAR(255) NOT NULL,
                        capacidade INTEGER NOT NULL,
                        vagas_disponiveis INTEGER NOT NULL
);

CREATE TABLE ingresso (
                          id BIGSERIAL PRIMARY KEY,
                          evento_id BIGINT NOT NULL,
                          participante_id BIGINT NOT NULL,
                          data_compra TIMESTAMP NOT NULL,

                          CONSTRAINT fk_evento
                              FOREIGN KEY (evento_id)
                                  REFERENCES evento(id),

                          CONSTRAINT fk_participante
                              FOREIGN KEY (participante_id)
                                  REFERENCES participante(id)
);
