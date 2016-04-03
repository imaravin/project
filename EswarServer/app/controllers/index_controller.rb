class IndexController < ApplicationController
  def cal
	unit=params[:unit]
	userid=params[:user]
 
	@user=userid
	@unt	=unit
	@amount=unit.to_i * 5
	#render :xml => h
  end
end
