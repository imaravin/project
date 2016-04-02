class IndexController < ApplicationController
  def cal
	unit=params[:unit]
	userid=params[:user]
	h={}
	h[:user]=userid
	h[:unit]	=unit
	h[:amount]=unit.to_i * 5
	render :xml => h
  end
end
