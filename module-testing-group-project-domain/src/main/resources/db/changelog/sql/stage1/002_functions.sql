--GO
--DROP VIEW cariages_car_count_view
--SELECT * FROM cariages_car_count_view;
--SELECT * FROM cariages_car_count_view WHERE departureCount = (SELECT MAX(departureCount) FROM cariages_car_count_view);

GO
CREATE FUNCTION GetFreeTasks()
RETURNS TABLE
AS
RETURN
SELECT *
FROM [task] AS t
WHERE t.status_id = 1

--DROP FUNCTION GetFreeTasks
--GO
--SELECT * FROM dbo.GetFreeTasks()

GO
CREATE FUNCTION GetMineFinishedTasks(@driver_id int)
RETURNS TABLE
AS
RETURN
SELECT *
FROM [task] AS t
WHERE t.driver_id = @driver_id AND t.status_id = 5

--DROP FUNCTION GetMineFinishedTasks
--GO
--SELECT * FROM dbo.GetMineFinishedTasks(2)

GO
CREATE FUNCTION GetMineCurrentTasks(@driver_id int)
RETURNS TABLE
AS
RETURN
SELECT *
FROM [task] AS t
WHERE t.driver_id = @driver_id AND t.status_id = 2

--DROP FUNCTION GetMineCurrentTasks
--GO
--SELECT * FROM dbo.GetMineCurrentTasks(2)

GO
CREATE FUNCTION GetReportsByDrivarIdAndTaskId(@driver_id int, @task_id int)
RETURNS TABLE
AS
RETURN
SELECT *
FROM [task_report] AS tr
LEFT JOIN
[task] as t
ON t.id = tr.task_id
WHERE t.driver_id = @driver_id AND t.id = @task_id

--DROP FUNCTION GetReportsByDrivarIdAndTaskId
--GO
--SELECT * FROM dbo.GetReportsByDrivarIdAndTaskId(3, 2)

GO
CREATE FUNCTION GetReportsForActiveTask(@driver_id int)
RETURNS TABLE
AS
RETURN
SELECT * FROM report WHERE id IN
(SELECT reports_id FROM task_report where task_id = (SELECT top 1 id FROM task WHERE driver_id = @driver_id AND status_id = 2))

--DROP FUNCTION GetReportsForActiveTask
--GO
--SELECT * FROM dbo.GetReportsForActiveTask(2)
