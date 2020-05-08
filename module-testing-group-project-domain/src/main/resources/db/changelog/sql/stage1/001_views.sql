--1. Создать представление, содержащее полную информацию о водителе.
GO
CREATE VIEW FullUserView([id], [first_name], [last_name], [patronymic], [birthday], [login], [password], [money], [role_id], [status_id], [name]) AS
SELECT u.id, u.first_name, u.last_name, u.patronymic, u.birthday, u.login, u.password, u.money, u.role_id, u.status_id, s.name FROM
[carriages_system].[dbo].[user] AS u
left join
user_status AS s
ON u.status_id = s.id

--SELECT * FROM FullUserView;
--DROP VIEW FullUserView

--2. Создать представление, содержащее информацию о среднем километраже выездов
--каждого водителя за два года. С помощью созданного представления определить фамилии
--водителей, имеющих наименьший километраж и наибольший средний километраж.

GO
CREATE VIEW cariages_min_max_distance_view ([driverId], [distance])
AS SELECT (driver1.last_name + ' ' + driver1.first_name + ' ' + driver1.patronymic), AVG(distance)
FROM
	(SELECT * FROM
	task as t
	LEFT JOIN
	(SELECT departure, distance, arrival, task_id, reports_id FROM
	report AS r
	LEFT JOIN
	task_report AS tr
	ON tr.reports_id = r.id) AS rtr
	ON rtr.task_id = t.id) AS journal1
INNER JOIN
[carriages_system].[dbo].[user] AS driver1
ON journal1.driver_id = driver1.id
WHERE getdate() >  DATEPART(m, DATEADD(m, -24, getdate())) GROUP BY (driver1.last_name + ' ' + driver1.first_name + ' ' + driver1.patronymic);

--SELECT * FROM cariages_min_max_distance_view ORDER BY distance DESC;
--GO
--DROP VIEW cariages_min_max_distance_view

--3. Создать представление, содержащее информацию об использовании автомобилей за
--за два года. С помощью созданного представления определить автомобиль,
--который имеет максимальное число выездов.

GO
CREATE VIEW cariages_car_count_view ([name], departureCount)
AS
SELECT (b.brand + ' ' + b.model + ' ' + result2.number), COUNT(departure) as departureCount FROM [carriages_system].[dbo].[Brand] AS b
INNER JOIN
(SELECT car1.brand_id, car1.number, journal1.*
FROM
	(SELECT * FROM
	task as t
	LEFT JOIN
	(SELECT departure, distance, arrival, task_id, reports_id FROM
	report AS r
	LEFT JOIN
	task_report AS tr
	ON tr.reports_id = r.id) AS rtr
	ON rtr.task_id = t.id) AS journal1
LEFT JOIN
[carriages_system].[dbo].[Car] AS car1
ON journal1.car_id = car1.id) AS result2
ON result2.brand_id = b.id
WHERE getdate() > DATEPART(m, DATEADD(m, -24, getdate()))  GROUP BY (b.brand + ' ' + b.model + ' ' + result2.number)