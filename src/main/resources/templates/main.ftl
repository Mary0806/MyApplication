<#import "parts/common.ftl" as c>
<@c.page>

    <div class="form-row">
        <div class="form-group col-md-2">
            <form method="get" action="/main" >
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by tag">
                <button type="submit" class="btn btn-primary mr-6">Search</button>
            </form>
        </div>
    </div>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new Message
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="text" placeholder="Введите сообщение" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="tag" placeholder="Тэг">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>


    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
        </div>
    <#else>
        No messages
    </#list>>
</@c.page>