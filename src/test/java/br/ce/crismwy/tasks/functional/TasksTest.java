package br.ce.crismwy.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.12:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.0.12:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// Clicar em Add ToDo:
			driver.findElement(By.id("addTodo")).click();
			
			// Escrever a Descrição:
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
	
			// Escrever a Data:
			driver.findElement(By.id("dueDate")).sendKeys("25/09/2025");
			
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
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
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
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
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
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
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
	
	@Test
	public void deveRemoverTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// Inserir a Tarefa:
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("25/09/2030");
			driver.findElement(By.id("saveButton")).click();
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", mensagem);
			
			// Remover a Tarefa:
			driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();
			mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", mensagem);
		} finally {
			// Fechar o Navegador:
			driver.quit();
		}
	}
}
