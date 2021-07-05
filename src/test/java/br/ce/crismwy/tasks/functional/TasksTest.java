package br.ce.crismwy.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		try {
			// Clicar em Add ToDo:
			driver.findElement(By.id("addTodo")).click();
			
			// Escrever a Descrição:
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
	
			// Escrever a Data:
			driver.findElement(By.id("dueDate")).sendKeys("25/09/2021");
			
			// Clicar em Salvar:
			driver.findElement(By.id("saveButton")).click();
			
			// Validar a Mensagem de Sucesso:
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", mensagem);
		} finally {
			// Fechar o Navegador:
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();
		try {
			// Clicar em Add ToDo:
			driver.findElement(By.id("addTodo")).click();
	
			// Escrever a Data:
			driver.findElement(By.id("dueDate")).sendKeys("25/09/2021");
			
			// Clicar em Salvar:
			driver.findElement(By.id("saveButton")).click();
			
			// Validar a Mensagem de Sucesso:
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", mensagem);
		} finally {
			// Fechar o Navegador:
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();
		try {
			// Clicar em Add ToDo:
			driver.findElement(By.id("addTodo")).click();
	
			// Escrever a Descrição:
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// Clicar em Salvar:
			driver.findElement(By.id("saveButton")).click();
			
			// Validar a Mensagem de Sucesso:
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", mensagem);
		} finally {
			// Fechar o Navegador:
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();
		try {
			// Clicar em Add ToDo:
			driver.findElement(By.id("addTodo")).click();
			
			// Escrever a Descrição:
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
	
			// Escrever a Data:
			driver.findElement(By.id("dueDate")).sendKeys("25/09/2010");
			
			// Clicar em Salvar:
			driver.findElement(By.id("saveButton")).click();
			
			// Validar a Mensagem de Sucesso:
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", mensagem);
		} finally {
			// Fechar o Navegador:
			driver.quit();
		}
	}
}
