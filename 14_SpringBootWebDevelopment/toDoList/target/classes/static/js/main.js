$(function () {
    const appendTask = function (data) {
        var taskCode = '<a href="#" class="task-link" data-id="' +
            data.id + '">' + data.name + '</a><br>';
        $('#task-list')
            .append('<div class="task-div" id="' +  data.id + '">' + taskCode + '</div>');
    };


    $('#show-add-task-form').click(function () {
        $('#task-form').css('display', 'flex');
    });

    $('#task-form').click(function (event) {
        if (event.target === this) {
            $(this).css('display', 'none');
        }
    });

    $('#change-task-form').click(function(event) {
        if (event.target === this) {
            $(this).css('display', 'none');
        }
    });

    $(document).on('click', '.task-link', function () {
        var link = $(this);
        var taskId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/tasks/' + taskId,
            success: function (response) {
                var code = '<span>Описание: ' + response.description + '</span>';
                link.parent().append(code);
            },
            error: function (response) {
                if (response.status === 404) {
                    alert('Task not found!');
                }
            }
        });
        return false;
    });

    $('#save-task').click(function () {
        var task = {
            name: $('#task-form form input[name="name"]').val(),
            description: $('#task-form form input[name="description"]').val()
        }
        $.ajax({
            method: "POST",
            url: '/tasks/',
            data: JSON.stringify(task),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                $('#task-form').hide();
                appendTask(response);
                location.reload();
            },
        });
        return false;
    });

    $(document).on('click', '.delete-task', function() {
        var result = confirm('Вы точно хотите удалить запись?');
        if (result) {
            var link = $(this);
            var taskId = link.data('id');
            $.ajax({
                method: 'DELETE',
                url: '/tasks/' + taskId,
                success: function() {
                    $(this).remove();
                    location.reload();
                }
            });
        }
        return false;
    });

    $('.edit-task').click(function() {
        $('#change-task-form').css('display', 'flex');
        var link = $(this);
        var taskId = link.data('id');
        $(document).on('click', '#save-changed-task', function() {
            var result = confirm('Вы точно хотите перезаписать данные?');
            if (result) {
                var task = $('#change-task-form form').serialize();
                $.ajax({
                    method: 'PUT',
                    url: '/tasks/' + taskId,
                    data: task,
                    success: function(response) {
                        $('#task-form').css('display', 'none');
                        var task = {};
                        task.id = taskId;
                        var dataArray = $('#change-task-form form').serializeArray();
                        for (i in dataArray) {
                            task[dataArray[i]['name']] = dataArray[i]['value'];
                        }
                        appendTask(task);
                        location.reload();
                    }
                });
            }
            return false;
        });
    });
});