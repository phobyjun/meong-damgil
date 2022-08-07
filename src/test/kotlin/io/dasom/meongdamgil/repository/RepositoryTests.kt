package io.dasom.meongdamgil.repository

import io.dasom.meongdamgil.model.Account
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoryTests(
    val accountRepository: AccountRepository
) : BehaviorSpec({

    Given("계정 생성 후 repository에 등록하고") {
        val account = Account(email = "test@test.com", password = "testPassword")
        accountRepository.save(account)
        When("findAccountByEmail을 실행하면") {
            val foundAccount = accountRepository.findAccountByEmail(account.email)
            Then("해당 계정을 반환한다") {
                foundAccount shouldBe account
            }
        }

        When("existsAccountByEmail을 실행하면") {
            val exist = accountRepository.existsAccountByEmail("test@test.com")
            val notExist = accountRepository.existsAccountByEmail("abcd@test.com")
            Then("계정이 있을 경우 true를 반환한다") {
                exist shouldBe true
            }
            Then("계정이 없을 경우 false를 반환한다") {
                notExist shouldBe false
            }
        }
    }

})
