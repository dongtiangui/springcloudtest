<h1>系统状态</h1>
<div class="row">
  <div class="col-md-6">
    <table id='instances' class="table table-condensed table-striped table-hover">
      <#if amazonInfo??>
        <tr>
          <td>服务注册</td>
          <td>AMI: ${amiId!}</td>
        </tr>
        <tr>
          <td>区域</td>
          <td>${availabilityZone!}</td>
        </tr>
        <tr>
          <td>实例ID</td>
          <td>${instanceId!}</td>
        </tr>
      </#if>
      <tr>
        <td>环境</td>
        <td>${environment!}</td>
      </tr>
      <tr>
        <td>数据中心</td>
        <td>${datacenter!}</td>
      </tr>
    </table>
  </div>
  <div class="col-md-6">
    <table id='instances' class="table table-condensed table-striped table-hover">
      <tr>
        <td>当前时间</td>
        <td>${currentTime}</td>
      </tr>
      <tr>
        <td>更新时间</td>
        <td>${upTime}</td>
      </tr>
      <tr>
        <td>已启用租约到期</td>
        <td>${registry.leaseExpirationEnabled?c}</td>
      </tr>
      <tr>
        <td>更新门槛</td>
        <td>${registry.numOfRenewsPerMinThreshold}</td>
      </tr>
      <tr>
        <td>最后续约时间</td>
        <td>${registry.numOfRenewsInLastMin}</td>
      </tr>
    </table>
  </div>
</div>

<#if isBelowRenewThresold>
    <#if !registry.selfPreservationModeEnabled>
        <h4 id="uptime"><font size="+1" color="red"><b>续约比阈值更重要。 自我保存模式已关闭。 在网络/其他问题的情况下，这可能无法保护实例。</b></font></h4>
    <#else>
        <h4 id="uptime"><font size="+1" color="red"><b>紧急！ 当EUREKA不是时，EUREKA可能会不正当地提出申请。 更新的数量超过了阈值，并且这些实际情况并未到期，只是为了安全起见。</b></font></h4>
    </#if>
<#elseif !registry.selfPreservationModeEnabled>
    <h4 id="uptime"><font size="+1" color="red"><b>自我保存模式已关闭。 在网络/其他问题的情况下，这可能无法保护实例。</b></font></h4>
</#if>

<h1>DS Replicas</h1>
<ul class="list-group">
  <#list replicas as replica>
    <li class="list-group-item"><a href="${replica.value}">${replica.key}</a></li>
  </#list>
</ul>

