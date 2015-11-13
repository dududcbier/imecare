--Modelo físico

CREATE TABLE Convenio (
nome varchar(50) PRIMARY KEY
);
  

CREATE TABLE Procedimento (
codigo_anvisa int PRIMARY KEY,
nome char(30),
descricao varchar(256)
);
 
CREATE TABLE Pessoa (
cpf char(11) UNIQUE,
rg char(15),
nome varchar(50) UNIQUE,
dt_nascimento date,
parentesco char(20),
PRIMARY KEY(cpf, nome)
);

CREATE TABLE Email(
CPF char(11),
nome varchar(50),
email char(40),
PRIMARY KEY(email, CPF, nome),
FOREIGN KEY (CPF, nome) REFERENCES Pessoa(CPF, nome) ON DELETE CASCADE ON UPDATE CASCADE
); 

CREATE TABLE Telefone_emergencia(
nome_pessoa varchar(50),
nome_contato varchar(50),
numero int,
parentesco char(20),
PRIMARY KEY(nome_pessoa, nome_contato, numero, parentesco),
FOREIGN KEY(nome_pessoa) REFERENCES Pessoa(nome) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Telefone(
nome varchar(50),
numero int ,
PRIMARY KEY(nome, numero),
FOREIGN KEY(nome) REFERENCES Pessoa(nome) ON UPDATE CASCADE 
);


CREATE TABLE Paciente (
tipo CHAR(3),
cpf char(11),
nome varchar(50),
PRIMARY KEY (cpf, nome),
FOREIGN KEY(cpf, nome) REFERENCES Pessoa (cpf, nome) ON UPDATE CASCADE ON DELETE CASCADE
);

ALTER TABLE Paciente ADD CHECK (tipo = 'A+' OR tipo = 'A-' OR tipo = 'B+' OR tipo = 'B-' OR tipo = 'AB+' OR tipo = 'AB-' OR tipo = 'O+' OR tipo = 'O-');


CREATE TABLE Medico (
CRM INT,
nome varchar(50),
cpf char(11),
PRIMARY KEY (CRM),
FOREIGN KEY(cpf, nome) REFERENCES Pessoa (cpf, nome) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Especialidade(
CRM_med int,
CPF_med char(11),
nome varchar(50),
espec varchar(50),
PRIMARY KEY(CRM_med, CPF_med, nome, espec),
FOREIGN KEY(CRM_med, CPF_med, nome) REFERENCES Medico(CRM, CPF, nome) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Local (
cep int,
numero int,
complemento char(15),
logradouro varchar(40),
cidade varchar(40),
estado varchar(40),
PRIMARY KEY(cep, numero, complemento)
);
CREATE TABLE Reside_em (
cep int,
numero int,
complemento char(15),
cpf_pessoa char(11),
nome varchar(50),
PRIMARY KEY(cep, numero, complemento, cpf_pessoa, nome),
FOREIGN KEY(cep,numero,complemento) REFERENCES Local (cep, numero, complemento) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY(cpf_pessoa, nome) REFERENCES Pessoa (cpf, nome) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Doenca (
CID CHAR(10) PRIMARY KEY,
nome CHAR(50)
);

CREATE TABLE Sintoma(
nome CHAR(40) PRIMARY KEY,
descricao CHAR(256)
);

CREATE TABLE Principio_Ativo (
substancia CHAR(40) PRIMARY KEY,
inespecifico CHAR(40),
especifico CHAR(40)
);

CREATE TABLE Medicamento (
nome CHAR(50) PRIMARY KEY,
tarja CHAR(40)
);

CREATE TABLE Compoe(
substancia char(40),
nome_medicamento char(50),
concentracao char(10),
PRIMARY KEY(substancia, nome_medicamento),
FOREIGN KEY(substancia) REFERENCES Principio_Ativo(substancia),
FOREIGN KEY(nome_medicamento) REFERENCES Medicamento(nome)
);

CREATE TABLE Possui (
CID char(10),
nome_sintoma CHAR(40),
PRIMARY KEY (CID,  nome_sintoma),
FOREIGN KEY (CID) REFERENCES Doenca(CID) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (nome_sintoma) REFERENCES Sintoma(nome) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE E_Tratada_pelo(
CID char(10),
nome_medicamento char(50),
PRIMARY KEY (CID, nome_medicamento),
FOREIGN KEY(nome_medicamento) REFERENCES Medicamento(nome) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(CID) REFERENCES Doenca(CID) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Estabelecimento_Medico(
cnpj char(30) PRIMARY KEY,
nome varchar(50),
cep int,
complemento char(15),
numero int
);

CREATE TABLE Telefone_Estabelecimento (
telefone int,
cnpj char(30),
PRIMARY KEY(telefone, cnpj),
FOREIGN KEY (cnpj) REFERENCES Estabelecimento_Medico(cnpj) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Trabalha_Para(
CPF CHAR(11),
nome varchar(50),
CRM INT,
cnpj char(30),
PRIMARY KEY(CPF, cnpj),
FOREIGN KEY (CPF, nome, CRM) REFERENCES Medico(CPF, nome, CRM) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cnpj) REFERENCES Estabelecimento_Medico(cnpj) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Diagnosticada_Em(
CID char(10),
cpf_paciente char(11),
nome varchar(50),
cronica BOOLEAN,
inicio DATE,
fim DATE,
PRIMARY KEY (CID, cpf_paciente, nome),
FOREIGN KEY (CID) REFERENCES Doenca(CID) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (nome, cpf_paciente) REFERENCES Paciente(nome, cpf) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Atende (
CPF_Medico char(11),
nome_Medico varchar(50),
CRM INT,
CPF_Paciente char(11),
nome_paciente varchar(50),
PRIMARY KEY (CPF_Medico, CRM, nome_Medico, CPF_Paciente, nome_Paciente),
FOREIGN KEY (CPF_Medico, CRM, nome_Medico) REFERENCES Medico(CPF, CRM, nome) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (CPF_Paciente, nome_Paciente) REFERENCES Paciente(CPF, nome) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE Atendimento(
CPF_Medico CHAR(11),
nome_med varchar(50),
CRM INT,
CPF_Paciente CHAR(11),
nome_paciente varchar(50),
data DATE,
horario TIME,
tipo_atendimento CHAR(20),
comentario VARCHAR(256),
PRIMARY KEY(CPF_Medico, nome_med, CRM, CPF_Paciente, nome_paciente, data, horario),
FOREIGN KEY (CPF_Medico, nome_med, CRM) REFERENCES Medico(CPF, nome, CRM) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (CPF_Paciente, nome_paciente) REFERENCES Paciente(CPF, nome) ON DELETE RESTRICT ON UPDATE CASCADE
);

ALTER TABLE Atendimento ADD CHECK (tipo_atendimento = 'aproveitado' OR tipo_atendimento = 'falso positivo' OR tipo_atendimento = 'refazer');


CREATE TABLE Plano (
nome varchar(30),
faixa_etaria char(30),
carencia boolean,
tipo varchar(30),
nome_convenio varchar(50),
PRIMARY KEY(tipo,nome_convenio),
FOREIGN KEY(nome_convenio) REFERENCES Convenio (nome) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE E_beneficiado_pelo (
cpf_paciente char(11),
nome varchar(50),
tipo_plano varchar(30),
nome_convenio varchar(30),
num_plano int,
dt_inicio date,
dt_fim date,
PRIMARY KEY(cpf_paciente, nome, tipo_plano, nome_convenio),
FOREIGN KEY(cpf_paciente, nome) REFERENCES Paciente (cpf, nome) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY(tipo_plano,nome_convenio) REFERENCES Plano (tipo,nome_convenio) ON UPDATE CASCADE
--ON DELETE NO ACTION: pode ser que o convenio tenha deixado de existir, mas mesmo assim, no passado a pessoa foi atendida por aquele convênio
);

CREATE TABLE E_Relatado(
nome_sintoma CHAR(40),
CPF_Medico CHAR(11),
nome_Medico varchar(50),
CRM INT,
CPF_Paciente CHAR(11),
nome_Paciente varchar(50),
data DATE,
horario TIME,
PRIMARY KEY(CPF_Medico, nome_Medico, CRM, CPF_Paciente, nome_Paciente, Data, Horario, nome_sintoma),
FOREIGN KEY (CPF_Paciente, nome_Paciente, nome_Medico, CPF_Medico, CRM, data, horario ) REFERENCES Atendimento(CPF_Paciente, nome_paciente, CPF_Medico, nome_med, CRM, data, horario) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (nome_sintoma) REFERENCES Sintoma(nome) ON DELETE CASCADE ON UPDATE CASCADE
);

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

CREATE TABLE Procedimento_Realizado(
data DATE,
horario TIME,
laudo varchar(256),
PRIMARY KEY(data, horario)
);


CREATE TABLE Atendimento_utiliza_plano(
tipo varchar(30),
nome_convenio varchar(50),
CPF_Medico CHAR(11),
nome_Medico varchar(50),
CRM INT,
CPF_Paciente CHAR(11),
nome_Paciente varchar(50),
data DATE,
horario TIME,
PRIMARY KEY(CPF_Medico, nome_Medico, CRM, CPF_Paciente, nome_Paciente, Data, Horario, tipo, nome_convenio),
FOREIGN KEY (CPF_Paciente, nome_Paciente, nome_Medico, CPF_Medico, CRM, data, horario ) REFERENCES Atendimento(CPF_Paciente, nome_paciente, CPF_Medico, nome_med, CRM, data, horario) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (tipo, nome_convenio) REFERENCES Plano(tipo, nome_convenio) ON DELETE CASCADE ON UPDATE CASCADE
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


CREATE TABLE Realiza (
cpf_paciente char(11),
nome_paciente varchar(50),
cnpj char(30) ,
codigo_anvisa int,

PRIMARY KEY(cpf_paciente, nome_paciente, cnpj, codigo_anvisa),
FOREIGN KEY(cpf_paciente, nome_paciente) REFERENCES Paciente (cpf, nome) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY(codigo_anvisa) REFERENCES Procedimento (codigo_anvisa),
FOREIGN KEY(cnpj) REFERENCES Estabelecimento_Medico (cnpj)
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


CREATE TABLE Solicita (
codigo_anvisa int,
CPF_Medico CHAR(11),
nome_Medico varchar(50),
CRM INT,
CPF_Paciente CHAR(11),
nome_Paciente varchar(50),
data DATE,
horario TIME,
PRIMARY KEY(CPF_Medico, nome_Medico, CRM, CPF_Paciente, nome_Paciente, Data, Horario, codigo_anvisa),
FOREIGN KEY (CPF_Paciente, nome_Paciente, nome_Medico, CPF_Medico, CRM, data, horario ) REFERENCES Atendimento(CPF_Paciente, nome_paciente, CPF_Medico, nome_med, CRM, data, horario) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (codigo_anvisa) REFERENCES Procedimento(codigo_anvisa) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE Arquivos (
arquivos varchar(500),
data date,
horario time,
cpf_paciente char(11),
codigo_procedimento int,
PRIMARY KEY(arquivos, data, horario,cpf_paciente,codigo_procedimento),
FOREIGN KEY(data, horario, cpf_paciente,codigo_procedimento) REFERENCES Realiza (data, horario, cpf_paciente, codigo_procedimento) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Analisa(
data_atendimento DATE,
horario_atendimento TIME,
data_procedimento DATE,
horario_procedimento TIME,
CPF_Medico CHAR(11),
nome_Medico varchar(50),
CRM INT,
CPF_Paciente CHAR(11),
nome_paciente varchar(50),
avaliacao varchar(256),
PRIMARY KEY(data_atendimento, horario_atendimento, CPF_Medico, nome_Medico, CRM, CPF_Paciente, nome_paciente, data_procedimento, horario_procedimento),
FOREIGN KEY(data_procedimento, horario_procedimento) REFERENCES Procedimento_Realizado(data, horario),
FOREIGN KEY (CPF_Paciente, nome_Paciente, nome_Medico, CPF_Medico, CRM, data_atendimento, horario_atendimento ) REFERENCES Atendimento(CPF_Paciente, nome_paciente, CPF_Medico, nome_med, CRM, data, horario) ON DELETE RESTRICT ON UPDATE CASCADE
);


CREATE TABLE Realizacao (
codigo_anvisa int,
cnpj char(30),
PRIMARY KEY(codigo_anvisa, cnpj),
FOREIGN KEY (cnpj) REFERENCES Estabelecimento_Medico(cnpj) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (codigo_anvisa) REFERENCES Procedimento(codigo_anvisa) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Depende_De(
tipo varchar(30),
nome_convenio varchar(50),
cnpj char(30),
codigo_anvisa int,
PRIMARY KEY(tipo, nome_convenio, cnpj, codigo_anvisa),
FOREIGN KEY (cnpj, codigo_anvisa) REFERENCES Realizacao(cnpj, codigo_anvisa) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (tipo, nome_convenio) REFERENCES Plano(tipo, nome_convenio) ON DELETE RESTRICT ON UPDATE CASCADE
);

