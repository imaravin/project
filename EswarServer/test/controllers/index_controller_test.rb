require 'test_helper'

class IndexControllerTest < ActionController::TestCase
  test "should get cal" do
    get :cal
    assert_response :success
  end

end
