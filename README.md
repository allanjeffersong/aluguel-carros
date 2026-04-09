# 🚗 Sistema de Aluguel de Carros

Projeto Java com **JUnit 5** e **JaCoCo** desenvolvido como avaliação prática de testes unitários automatizados.

---

## 📁 Estrutura do Projeto

```
aluguel-carros/
├── pom.xml
└── src/
    ├── main/java/com/aluguel/carros/
    │   ├── Carro.java                  ← Entidade
    │   └── AluguelCarroService.java    ← Regras de negócio
    └── test/java/com/aluguel/carros/
        └── AluguelCarroServiceTest.java ← 8 testes unitários
```

---

## 🛠️ Tecnologias

| Ferramenta | Versão   | Função                        |
|------------|----------|-------------------------------|
| Java       | 17       | Linguagem principal           |
| Maven      | 3.9+     | Gerenciamento de dependências |
| JUnit 5    | 5.10.2   | Framework de testes           |
| JaCoCo     | 0.8.11   | Cobertura de testes           |
| IntelliJ   | Qualquer | IDE recomendada               |

---

## ✅ Regras de Negócio Testadas

| ID     | Regra                                                                 | Teste correspondente                                    |
|--------|-----------------------------------------------------------------------|---------------------------------------------------------|
| RN-01  | Placa não pode ser nula nem vazia                                     | `deveLancarExcecaoQuandoPlacaVazia`                     |
| RN-02  | Modelo não pode ser nulo nem vazio                                    | `deveLancarExcecaoQuandoModeloVazio`                    |
| RN-03a | Ano deve ser ≥ 1886 (ano do primeiro automóvel)                       | `deveLancarExcecaoQuandoAnoAbaixoDoMinimo`              |
| RN-03b | Ano deve ser ≤ 2026 (ano corrente)                                    | `deveLancarExcecaoQuandoAnoAcimaDoMaximo`               |
| RN-04  | Não é possível alugar um carro que já está alugado                    | `deveLancarExcecaoAoAlugarCarroJaAlugado`               |
| RN-05  | Não é possível devolver um carro que não está alugado                 | `deveLancarExcecaoAoDevolverCarroNaoAlugado`            |
| RN-06  | A listagem retorna apenas carros disponíveis (não alugados)           | `deveListarApenasCarrosDisponiveis`                     |
| —      | Fluxo completo: cadastrar → alugar → devolver → disponível novamente  | `deveAlugarEDevolverCarroCorretamente`                  |

---

## ▶️ Como executar

### Pré-requisitos
- Java 17+
- Maven 3.9+ (`mvn -version` para verificar)

### 1. Clonar o repositório
```bash
git clone https://github.com/SEU_USUARIO/aluguel-carros.git
cd aluguel-carros
```

### 2. Compilar e rodar os testes
```bash
mvn test
```

### 3. Gerar relatório de cobertura JaCoCo
```bash
mvn verify
```
O relatório HTML é gerado em:
```
target/site/jacoco/index.html
```
Abra esse arquivo no navegador para ver a cobertura detalhada.

### 4. Rodar no IntelliJ
1. `File → Open` → selecione a pasta `aluguel-carros`
2. Aguarde o Maven importar as dependências
3. Clique com o botão direito em `AluguelCarroServiceTest.java` → **Run**
4. Para o relatório JaCoCo: `Run → Run with Coverage`

---

## 📊 Resultado dos Testes

```
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

> O print do relatório JaCoCo deve ser incluído aqui após executar `mvn verify`.
> O relatório fica em `target/site/jacoco/index.html`.

---

## 👤 Autor

Seu Nome — Disciplina de Engenharia de Software / Testes de Software
