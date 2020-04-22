GO
CREATE PROCEDURE TAKE_TASK
(@chosen_task_id int OUTPUT, @current_user_id int OUTPUT)
AS
BEGIN
DECLARE @random_free_car_id int;
SET @random_free_car_id = (SELECT TOP 1 id FROM car WHERE status_id = 1);
UPDATE task SET status_id = 2, car_id = @random_free_car_id, driver_id = @current_user_id WHERE id = @chosen_task_id;
UPDATE [carriages_system].[dbo].[user] SET status_id = 2 WHERE id = @current_user_id;
SELECT * FROM dbo.GetFreeTasks() ORDER BY id;
END

GO
CREATE PROCEDURE ADD_REPORT
(@current_task_id int OUTPUT, @current_user_id int OUTPUT, @departure DATETIME OUTPUT,  @weight int OUTPUT,  @distance int OUTPUT, @arrival DATETIME OUTPUT)
AS
BEGIN
DECLARE @current_report_id int;
SET @current_report_id = (SELECT IDENT_CURRENT('report') + 1);
SET IDENTITY_INSERT report ON;
INSERT INTO [dbo].[report] ([id],[departure],[weight],[distance], [arrival])
     VALUES (@current_report_id, @departure, @weight, @distance, @arrival);
INSERT INTO [dbo].[task_report] ([task_id],[reports_id])
VALUES (@current_task_id, @current_report_id);
SET IDENTITY_INSERT report OFF ;
SELECT * FROM dbo.GetReportsForActiveTask(@current_user_id)  ORDER BY id
END

GO
CREATE PROCEDURE SEND_TO_VALIDATION_TASK
(@chosen_task_id int OUTPUT)
AS
BEGIN
UPDATE task SET status_id = 3 WHERE id = @chosen_task_id;
SELECT * FROM dbo.GetFreeTasks() ORDER BY id;
END

GO
CREATE PROCEDURE VALIDATE_TASK_TO_REJECTED
(@chosen_task_id int OUTPUT)
AS
BEGIN
DECLARE @current_user_id int;
SET @current_user_id = (SELECT TOP 1 driver_id FROM task WHERE id = @chosen_task_id);
UPDATE task SET status_id = 4 WHERE id = @chosen_task_id;
UPDATE [carriages_system].[dbo].[user] SET status_id = 1 WHERE id = @current_user_id;
SELECT * FROM task ORDER BY id;
END

GO
CREATE PROCEDURE VALIDATE_TASK_TO_FINISHED
(@chosen_task_id int OUTPUT)
AS
BEGIN
DECLARE @reward float;
DECLARE @adminAmount float;
DECLARE @driverAmount float;
DECLARE @newAdminAmount float;
DECLARE @newDriverAmount float;
DECLARE @current_user_id int;
SET @current_user_id = (SELECT TOP 1 driver_id FROM task WHERE id = @chosen_task_id);
SET @reward = (SELECT t.reward FROM task as t WHERE t.id = @chosen_task_id);
SET @adminAmount = (SELECT u.money FROM [carriages_system].[dbo].[user] as u WHERE u.id = 1);
SET @driverAmount = (SELECT u.money FROM [carriages_system].[dbo].[user] as u WHERE u.id = @current_user_id);
SET @newAdminAmount = @adminAmount - @reward;
SET @newDriverAmount = @driverAmount + @reward;
UPDATE task SET status_id = 5 WHERE id = @chosen_task_id;
UPDATE [carriages_system].[dbo].[user] SET money = @newAdminAmount WHERE id = 1;
UPDATE [carriages_system].[dbo].[user] SET status_id = 1, money = @newDriverAmount WHERE id = @current_user_id;
SELECT * FROM task ORDER BY id;
END
