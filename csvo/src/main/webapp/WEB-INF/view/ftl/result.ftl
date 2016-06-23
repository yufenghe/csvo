<#if list?exists && list?size gt 0>
		<#list list as article>
			<div class="result c-container " id="1" srcid="1599" tpl="se_com_default" data-click="">
				<h3 class="t"><a data-click="" href="" target="_blank">${article.fileName}</a></h3>
				<div class="c-row c-gap-top-small">
					<div class="general_image_pic c-span6">
						<a class="c-img6" style="height: 75px" href="" target="_blank">
							<img class="c-img c-img6"
							src="https://ss0.baidu.com/73F1bjeh1BF3odCf/it/u=3947011533,1531171434&amp;fm=96&amp;s=308A7C32191EC4C80CD064DE0300C0B2"
							style="height: 75px;">
						</a>
					</div>
					<div class="c-span18 c-span-last">
						<div class="c-abstract">
							${article.content}
						</div>
						<div class="f13">
						<#--
							<a target="_blank"
								href=""
								class="c-showurl" style="text-decoration: none;">www.uimaker.com/uimake...&nbsp;</a>
							<div class="c-tools" id="tools_11141379474399928573_1" data-tools="">
								<a class="c-tip-icon"><i class="c-icon c-icon-triangle-down-g"></i></a>
							</div>
							<span class="c-icons-outer"><span class="c-icons-inner"></span></span>&nbsp;-&nbsp;<a
								data-click="{'rsv_snapshot':'1'}" href="" target="_blank"
								class="m">快照</a><span>&nbsp;-&nbsp;<a href=""
								target="_blank" class="m" data-click="{'rsv_comments':'1'}">64条评价</a></span>
						-->
						</div>
					</div>
				</div>
				<#--
				<div class="c-gap-top c-recommend" style="display: none;"
					data-extquery="引擎搜索网站模板&nbsp;html搜索页面怎么写&nbsp;html本地搜索&nbsp;">
					<i class="c-icon c-icon-bear-circle c-gap-right-small"></i><span
						class="c-gray">为您推荐：</span><a
						href=""
						target="_blank" class="res-gap-right16">引擎搜索网站模板</a><a
						href=""
						target="_blank" class="res-gap-right16">html搜索页面怎么写</a><a
						href=""
						target="_blank">html本地搜索</a>
				</div>
				-->
			</div>
		</#list>
		
	<#else>
		<li style="width: 100%;">
			<p style="display: block; text-align: center; font-size: 3em;
				 height: 80px; line-height: 80px;">
				你没有分享任何信息，赶快分享你的作品吧。
			</p>
		</li>
</#if>
