# Desafio DigitalBank

## Contexto
Todo cliente precisa solicitar uma proposta de criação de nova conta de pessoa física antes de qualquer outra coisa e este é justamente processo que precisamos implementar. Só que tal processo precisa ser dividido em várias etapas, caso o contrário o cliente seria obrigado a passar um grande número de informações e poderíamos perder tudo por conta de uma falha de internet, falta de bateria no celular etc. A ideia aqui é minimizar isso.

## Passo 1

## Necessidade
No primeiro passo precisamos de algumas informações básicas.

- nome do cliente
- sobrenome do cliente
- email
- data de nascimento
- cpf

## Restrições
nome do cliente é obrigatório
sobrenome do cliente é obrigatório
email é obrigatório e precisa ter formato de email
cpf é obrigatório e precisa respeitar o formato
data de nascimento obrigatório, no passado e tem que ter mais de 18 anos
email não pode ser duplicado
cpf não pode ser duplicado

## Resultado esperado
status 201 e header location preenchido para o próximo passo do cadastro
status 400 em caso de falha de qualquer validação e json de retorno com as informações.

## Passo 2

## Necessidade
- cep
- rua
- bairro
- complemento
- cidade(pode ser campo aberto)
- estado(pode ser campo aberto)

## Restrições
cep obrigatório e no formato adequado
rua obrigatório
bairro obrigatório
complemento obrigatório
cidade obrigatória
estado obrigatório
tudo que é obrigatório do passo 1 precisa estar correto

## Resultado esperado
status 201 e header location preenchido para o próximo passo do cadastro
status 400 em caso de falha de qualquer validação e json de retorno com as informações.

## Passo 3

## Necessidade
arquivo que representa a frente do cpf

##Restrições
o arquivo é obrigatório

## Questionamento...
Será que existe algum jeito melhor de tratar esses uploads do que fazer o arquivo chegar pela aplicação?

## Resultado esperado
status 201 e header location preenchido para o próximo passo do cadastro
status 400 em caso de falha de qualquer validação e json de retorno com as informações.
status 404 caso a proposta que supostamente deveria estar associada com esse passo não exista.
Se os passos anteriores não tiverem completos, retorne 422

## Passo 4

## Necessidade
Retornar um json com todas as informações da proposta para que a aplicação cliente exiba e a pessoa possa confirmar tudo!

## Restrição
Todos os passos anteriores precisam ter sido completados

## Resultado esperado
Em caso de aceite, informamos que vamos criar a conta dela e um email será enviado(próxima funcionalidade)
Em caso de não aceite o sistema registra aquela proposta e vai mandar um email implorando para ela aceitar(próxima funcionalidade)
Se os passos anteriores não tiverem completos, retorne 422

## Passo 5
## Necessidade
Caso a proposta seja aceita pelo cliente, uma nova conta deve ser criada em função daquela proposta. Essa nova conta tem as seguintes informações:

Agencia (4 dígitos e pode ser gerado aleatório)
Conta (8 dígitos e pode ser gerado aleatório)
Código do banco (3 dígitos e pode ser fixo)
A partir da conta, precisamos chegar na proposta que a originou.
Saldo igual a zero.
Com a conta criada, um email deve ser enviado para a dona da proposta informando a criação da nova conta e os respectivos dados.

## detalhes adicionais
O email não precisa ser real. Vai ser legal se o sistema ficar preparado para lidar com emails fake em dev e reais em produção.
O processo de criação de conta só acontece depois que um sistema externo aceita as informações do documento daquela pessoa. Como toda chamada remota, algo de errado pode acontecer. Precisamos tentar pelo menos 2x antes de desistir.
Caso o sistema externo de aceite de documentos não tenha liberado, precisamos deixar um status na proposta indicando que ela ainda não foi liberada.
Caso o sistema externo de aceite tenha liberado, além de criamos a conta deixamos um status na proposta informando que ela foi liberada.

## restrições
O processo de criação deve ser disparado de forma a não bloquear o retorno relativo ao aceite do usuário. A pessoa aceita e depois a conta é criada e o email informativo enviado. Isso é muito importante!

## Resultado esperado
status 200(conta vai ser criada)

## Primeiro acesso após aprovação
No primeiro acesso pós aprovação precisamos passar pelo processo de confirmação de identidade e criação de nova senha.

## Necessidade
Aqui vamos precisar de alguns passos para realizar o processo de aprovação:

Primeiro colocamos de novo nosso email e cpf
Recebemos um email com um token de 6 dígitos aleatório associado com aquela conta que deve ser passado para a criação da nova senha.
Agora precisamos criar uma senha de 8 dígitos que seja forte.
Finalmente a senha é criada e associada com a conta.
Depois que a senha for criada é importante que um email seja enviado para o(a) dono(a) da conta informando que a senha foi modificada.

## restrições
O token tem tempo de expiração configurável no sistema
O token só pode ser usado uma vez
A senha é obrigatória, tem 8 dígitos e é forte
A senha precisa ser gravada encodada com algum algoritmo de hash

## Resultado esperado
Caso o token tenha expirado, retorne 400
Caso o token já tenha sido usado retorne 400
Caso tudo dê certo, retorne 200.
O status de falha aqui poderia ser 422, que significa unprocessable entity... Mas confesso que não vejo muita utilidade nesse circo de status para apis convencionais.