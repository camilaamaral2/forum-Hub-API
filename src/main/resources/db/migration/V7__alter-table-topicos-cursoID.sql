ALTER TABLE TOPICO
ADD CONSTRAINT fk_curso_id
FOREIGN KEY (curso_id)
REFERENCES CURSO (id)