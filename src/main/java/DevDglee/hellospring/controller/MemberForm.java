package DevDglee.hellospring.controller;

class MemberForm{
        String name;
//      createMemberForm 내부의 <input>태그의 name="name"이라고 썼기 때문에, 클래스 에서도 name으로 받는다.
//      *만약 name="userName" 으로 작성했으면, 클래스에서도 String userName; 으로 작성하여 받아올 수 있다.

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}