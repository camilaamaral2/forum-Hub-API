ALTER TABLE usuario DROP CONSTRAINT usuario_perfil_id_fkey;

ALTER TABLE usuario ALTER COLUMN perfil_id TYPE VARCHAR(255);

DROP TABLE perfil;