package com.soskin.store.springstore.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebApplication {
	// 1. Разобраться с кодом
	// 2. Добавить микросервис рекомендаций:
	// - 5 наиболее покупаемых продуктов за месяц
	// - 5 наиболее складываемых в корзину продуктов за день
	// - Вывести на главной странице эти рекомендации в виде текста

	//Так как с докером пока не работаем, вариант с дополнительной базой данных для рекомендаций отметен.
	//Раз своей бд нет, пришлось отступить от жестких правил мс архитектуры и внести изменения в Core.
	//Задачу решаем "одним запросом в бд" из order-repo отвечающим условиям дз (5 наиболее покупаемых продуктов за месяц).
	//Создаем доп эндпоинт "recommendations" в orders-controller, прокидываем запрос в order-service от туда в order-repo (где
	// прописываем отдельный запрос в бд).



	public static void main(String[] args) {
		SpringApplication.run(SpringWebApplication.class, args);
	}
}
