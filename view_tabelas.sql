--Visão e tabelas utilizadas para montar o bd (esquema físico)

CREATE VIEW prontuario AS
SELECT  p.*, d.CID, d.nome AS nome_doenca, s.nome AS nome_sintoma, s.descricao AS descricao_sintoma, 
        m.nome AS nome_medicamento, m.tarja, e.codigo_anvisa, e.nome AS nome_exame, e.descricao 
FROM Paciente p, Doenca d, Doenca_Diagnosticada dd, Possui po, Sintoma s, Prescreve pr, Medicamento m, Realiza r, Exame e
WHERE p.cpf = dd.CPF_Paciente AND dd.CID = d.CID AND  d.CID = po.CID AND po.nome_sintoma = s.nome
      AND m.nome = pr.nome_medicamento AND p.cpf= pr.CPF_Paciente AND p.cpf = r.cpf_paciente AND r.codigo_anvisa = e.codigo_anvisa;




CREATE OR REPLACE FUNCTION PacienteInexistente()
	RETURNS TRIGGER AS $$
BEGIN
	IF(NEW.cpf = null OR NEW.cpf NOT IN (SELECT cpf FROM Paciente)) THEN
	 	RETURN NULL;
	END IF;	
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION PacienteInexistente()
	RETURNS TRIGGER AS $$
BEGIN
	IF(NEW.cpf = null) THEN
	 	RETURN NULL;
	END IF;	
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;



CREATE TRIGGER InsereProntuario
INSTEAD OF INSERT OR UPDATE ON  prontuario
FOR EACH ROW
EXECUTE PROCEDURE PacienteInexistente();




CREATE OR REPLACE FUNCTION InsercaoProntuario()
RETURNS TRIGGER AS $$
BEGIN

 insert into prontuario values ('A+', 'Joao', '12345678', '31421', 'Diabetes', 'Sede', 'Sensacao constante de sede', 'Insulon', 
 	'vermelha', 2353, 'Exame de sangue', 'Será analisada a taxa de glicemia no sangue');
	RETURN NEW;

	RETURN NULL;
END;
$$ LANGUAGE plpgsql;




CREATE TABLE Paciente (
tipo CHAR(3),
cpf char(11),
nome varchar(50),
PRIMARY KEY (cpf, nome),
FOREIGN KEY(cpf, nome) REFERENCES Pessoa (cpf, nome) ON UPDATE CASCADE ON DELETE CASCADE
);

ALTER TABLE Paciente ADD CHECK (tipo = 'A+' OR tipo = 'A-' OR tipo = 'B+' OR tipo = 'B-' OR tipo = 'AB+' OR tipo = 'AB-' OR tipo = 'O+' OR tipo = 'O-');


CREATE TABLE Doenca_Diagnosticada(
CID char(10),
CPF_Medico CHAR(11),
nome_Medico varchar(50),
CRM INT,
CPF_Paciente CHAR(11),
nome_Paciente varchar(50),
data DATE,
horario TIME,
PRIMARY KEY(CPF_Medico, nome_Medico, CRM, CPF_Paciente, nome_Paciente, Data, Horario,CID),
FOREIGN KEY (CPF_Paciente, nome_Paciente, nome_Medico, CPF_Medico, CRM, data, horario ) REFERENCES Atendimento(CPF_Paciente, nome_paciente, CPF_Medico, nome_med, CRM, data, horario) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (CID) REFERENCES Doenca(CID) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Doenca (
CID CHAR(10) PRIMARY KEY,
nome CHAR(50)
);


CREATE TABLE Possui (
CID char(10),
nome_sintoma CHAR(40),
PRIMARY KEY (CID,  nome_sintoma),
FOREIGN KEY (CID) REFERENCES Doenca(CID) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (nome_sintoma) REFERENCES Sintoma(nome) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Sintoma(
nome CHAR(40) PRIMARY KEY,
descricao CHAR(256)
);


CREATE TABLE Medicamento (
nome CHAR(50) PRIMARY KEY,
tarja CHAR(40)
);


CREATE TABLE Procedimento_Realizado(
data DATE,
horario TIME,
laudo varchar(256),
PRIMARY KEY(data, horario)
);


CREATE TABLE Atendimento_E_Realizado(
cnpj char(30),
CPF_Medico CHAR(11),
nome_Medico varchar(50),
CRM INT,
CPF_Paciente CHAR(11),
nome_Paciente varchar(50),
data DATE,
horario TIME,
PRIMARY KEY(CPF_Medico, nome_Medico, CRM, CPF_Paciente, nome_Paciente, Data, Horario, cnpj),
FOREIGN KEY (CPF_Paciente, nome_Paciente, nome_Medico, CPF_Medico, CRM, data, horario ) REFERENCES Atendimento(CPF_Paciente, nome_paciente, CPF_Medico, nome_med, CRM, data, horario) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (cnpj) REFERENCES Estabelecimento_Medico(cnpj) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Prescreve(
nome_medicamento char(50),
CPF_Medico CHAR(11),
nome_Medico varchar(50),
CRM INT,
CPF_Paciente CHAR(11),
nome_Paciente varchar(50),
data DATE,
horario TIME,
dose char(15),
periodo TIME,
frequencia char(30),
PRIMARY KEY(CPF_Medico, nome_Medico, CRM, CPF_Paciente, nome_Paciente, Data, Horario, nome_medicamento),
FOREIGN KEY (CPF_Paciente, nome_Paciente, nome_Medico, CPF_Medico, CRM, data, horario ) REFERENCES Atendimento(CPF_Paciente, nome_paciente, CPF_Medico, nome_med, CRM, data, horario) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (nome_medicamento) REFERENCES Medicamento(nome) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Exame (
codigo_anvisa int PRIMARY KEY,
nome char(30),
descricao varchar(256)
);


CREATE TABLE Realiza (
cpf_paciente char(11),
nome_paciente varchar(50),
codigo_anvisa int,

PRIMARY KEY(cpf_paciente, nome_paciente, codigo_anvisa),
FOREIGN KEY(cpf_paciente, nome_paciente) REFERENCES Paciente (cpf, nome) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY(codigo_anvisa) REFERENCES Exame (codigo_anvisa)
);
