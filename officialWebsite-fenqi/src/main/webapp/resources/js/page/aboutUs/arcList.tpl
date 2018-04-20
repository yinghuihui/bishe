{{#each list}}
<li>
    <img src="{{thumbnail}}" />
    <div>
        <p class="mTitle">
            <a href="/modules/web/AboutUs/detail.htm?id={{id}}">{{title}}</a>
        </p>
        <h3>{{addTime}}</h3>
        <p class="mContent">{{description}}</p>
    </div>
</li>
{{/each}}